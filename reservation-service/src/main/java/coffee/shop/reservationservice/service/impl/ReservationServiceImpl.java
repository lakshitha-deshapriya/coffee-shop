package coffee.shop.reservationservice.service.impl;

import coffee.shop.reservationservice.dao.ReservationDao;
import coffee.shop.reservationservice.dto.Queue;
import coffee.shop.reservationservice.dto.QueueEntry;
import coffee.shop.reservationservice.dto.Reservation;
import coffee.shop.reservationservice.exception.NotFoundException;
import coffee.shop.reservationservice.exception.ReservationFailedException;
import coffee.shop.reservationservice.exception.ValidationException;
import coffee.shop.reservationservice.mapper.ReservationInverseMapper;
import coffee.shop.reservationservice.mapper.ReservationMapper;
import coffee.shop.reservationservice.repository.ReservationRepository;
import coffee.shop.reservationservice.service.ReservationService;
import coffee.shop.reservationservice.util.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

/**
 * Service bean to handle reservation related functionalities
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ReservationInverseMapper reservationInverseMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${custom.url.queue-service-url}")
    private String queueServiceUrl;

    @Value("${custom.log.name}")
    private String logName;

    private Logger logger;

    @PostConstruct
    public void setupLogger() {
        logger = LoggerFactory.getLogger(logName + " : ReservationService : ");
    }

    /**
     * Retrieve reservation by reservation Id
     *
     * @param reservationId Id of the reservation
     * @return Reservation Dto
     * @throws NotFoundException when the reservation could not found
     */
    @Override
    public Reservation getReservationById(long reservationId) throws NotFoundException {
        ReservationDao reservationDao = reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("Reservation not found"));
        return reservationMapper.mapReservation(reservationDao);
    }

    /**
     * Create a reservation
     *
     * @param body Reservation Dto
     * @return saved Reservation Dto
     * @throws ValidationException        when there are validation exceptions
     * @throws ReservationFailedException when the reservation failed
     */
    @Override
    public Reservation createReservation(Reservation body) throws ValidationException, ReservationFailedException {
        //Find the quickest queue for the shop
        Queue queue = getQuickestQueueForShop(body.getShopId());

        //Save the reservation
        ReservationDao reservationDao = reservationInverseMapper.mapReservation(body);
        ReservationDao savedReservation = saveReservation(reservationDao, OrderStatus.CONFIRMED.getStatus());

        //Add to Queue
        addToQueue(new QueueEntry(queue.getQueueId(), savedReservation.getReservationId()));

        //Update reservation status
        savedReservation = saveReservation(savedReservation, OrderStatus.IN_QUEUE.getStatus());

        return reservationMapper.mapReservation(savedReservation);
    }

    /**
     * saving the reservation
     *
     * @param reservationDao Reservation Dao
     * @param status         status of the reservation
     * @return saved reservation Dao
     */
    @Transactional
    public ReservationDao saveReservation(ReservationDao reservationDao, int status) {
        reservationDao.setOrderStatus(status);
        if (reservationDao.getReservationItems() != null) {
            reservationDao.getReservationItems().forEach(item -> item.setReservation(reservationDao));
        }
        return reservationRepository.save(reservationDao);
    }

    /**
     * Update status of the Reservation
     *
     * @param reservationId reservation id
     * @param status        status of the reservation
     * @return success or not
     * @throws NotFoundException reservation not found
     * @throws ValidationException when there is a validation exception
     */
    @Override
    @Transactional
    public boolean updateStatus(long reservationId, int status) throws NotFoundException, ValidationException, ReservationFailedException {
        if (!OrderStatus.validateStatus(status)) {
            throw new ValidationException("Invalid order status");
        }
        Optional<ReservationDao> reservationDao = reservationRepository.findById(reservationId);
        if (reservationDao.isPresent()) {
            ReservationDao reservation = reservationDao.get();
            if (removeQueueEntry(reservationId)) {
                reservation.setOrderStatus(status);
                reservationRepository.save(reservation);
            }
            return true;
        }
        throw new NotFoundException("Reservation not found");
    }

    /**
     * Cancelling a reservation
     * @param reservationId reservation Id
     * @throws NotFoundException reservation not found
     * @throws ReservationFailedException reservation has failed
     */
    @Override
    @Transactional
    public void cancelReservation(long reservationId) throws NotFoundException, ReservationFailedException {
        Optional<ReservationDao> reservationDao = reservationRepository.findById(reservationId);
        if (reservationDao.isPresent()) {
            ReservationDao reservation = reservationDao.get();
            if (reservation.getOrderStatus() != OrderStatus.COMPLETED.getStatus() || reservation.getOrderStatus() != OrderStatus.CANCELLED.getStatus()) {
                if (removeQueueEntry(reservationId)) {
                    reservation.setOrderStatus(OrderStatus.CANCELLED.getStatus());
                    reservationRepository.save(reservation);
                }
            }
            return;
        }
        throw new NotFoundException("Reservation not found");
    }

    /**
     * Retrieve the quickest Queue for a shop from queue-service
     *
     * @param shopId shopId
     * @return Quickest queue
     * @throws ValidationException when there is a validation exception
     */
    private Queue getQuickestQueueForShop(long shopId) throws ValidationException {
        try {
            URI bestQueueUri = UriComponentsBuilder
                    .fromHttpUrl(queueServiceUrl + "queues/available/" + shopId)
                    .build().toUri();

            HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

            //Get data from queue-service
            ResponseEntity<Queue> exchange = restTemplate.exchange(
                    bestQueueUri,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Queue>() {
                    });

            //Success Response
            if ((exchange.getStatusCode() == HttpStatus.OK) && exchange.getBody() != null) {
                return exchange.getBody();
            }

        } catch (RestClientException re) {
            logger.error(re.getMessage());
        }
        throw new ValidationException("Queue could not be found");
    }

    /**
     * Add queue to queue-service queue entry
     *
     * @param queueEntry queue entry Dto
     * @return added queue entry
     * @throws ReservationFailedException when the reservation failed
     */
    private QueueEntry addToQueue(QueueEntry queueEntry) throws ReservationFailedException {
        try {
            URI addToQueueUri = UriComponentsBuilder
                    .fromHttpUrl(queueServiceUrl + "queueEntry/")
                    .build().toUri();

            HttpEntity<QueueEntry> entity = new HttpEntity<>(queueEntry);

            ResponseEntity<QueueEntry> exchange = restTemplate.exchange(
                    addToQueueUri,
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<QueueEntry>() {
                    });

            if (exchange.getStatusCode() == HttpStatus.CREATED && exchange.getBody() != null) {
                return exchange.getBody();
            }
        } catch (RestClientException re) {
            logger.error("Error while adding to queue : " + re.getMessage());
        }
        throw new ReservationFailedException("Error while adding to queue");
    }

    /**
     * Remove queue entry from queue-service
     * @param reservationId reservation Id
     * @return success or not
     * @throws ReservationFailedException reservation has failed
     */
    private boolean removeQueueEntry(long reservationId) throws ReservationFailedException {
        try {
            URI removeQueueEntryUri = UriComponentsBuilder
                    .fromHttpUrl(queueServiceUrl + "queueEntry/" + reservationId)
                    .build().toUri();

            HttpEntity<QueueEntry> entity = new HttpEntity<>(new HttpHeaders());

            ResponseEntity exchange = restTemplate.exchange(
                    removeQueueEntryUri,
                    HttpMethod.DELETE,
                    entity,
                    new ParameterizedTypeReference() {
                    });

            if (exchange.getStatusCode() != HttpStatus.NO_CONTENT) {
                //Not Successful
                logger.error("Queue entry remove failed");
                throw new ReservationFailedException("Queue entry remove failed");
            }
            return true;
        } catch (RestClientException re) {
            logger.error("Queue entry remove failed");
        }
        throw new ReservationFailedException("Queue entry remove failed");
    }
}
