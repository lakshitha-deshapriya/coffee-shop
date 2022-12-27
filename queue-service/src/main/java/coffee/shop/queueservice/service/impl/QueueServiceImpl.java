package coffee.shop.queueservice.service.impl;

import coffee.shop.queueservice.dao.QueueDao;
import coffee.shop.queueservice.dao.QueueEntryDao;
import coffee.shop.queueservice.dao.QueueEntryIdClass;
import coffee.shop.queueservice.dto.Queue;
import coffee.shop.queueservice.dto.QueueEntry;
import coffee.shop.queueservice.exception.NotFoundException;
import coffee.shop.queueservice.exception.QueueNotFoundException;
import coffee.shop.queueservice.mapper.QueueInverseMapper;
import coffee.shop.queueservice.mapper.QueueMapper;
import coffee.shop.queueservice.repository.QueueEntryRepository;
import coffee.shop.queueservice.repository.QueueRepository;
import coffee.shop.queueservice.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Service bean to handle queue related functionalities
 */
@Service
public class QueueServiceImpl implements QueueService {
    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private QueueEntryRepository queueEntryRepository;

    @Autowired
    private QueueMapper queueMapper;

    @Autowired
    private QueueInverseMapper queueInverseMapper;

    @Value("${custom.log.name}")
    private String logName;

    private Logger logger;

    @PostConstruct
    public void setupLogger() {
        logger = LoggerFactory.getLogger(logName + " : Queue Service : ");
    }

    /**
     * get queue by queue Id
     * @param queueId Id of the queue
     * @return Queue Dto
     * @throws NotFoundException when queue is not found
     */
    @Override
    public Queue getQueueById(long queueId) throws NotFoundException {
        QueueDao queueDao = queueRepository.findById(queueId).orElseThrow(() -> new NotFoundException("Queue Id not found"));
        return queueMapper.mapQueue(queueDao);
    }

    /**
     * get queues of a shop
     * @param shopId shop Id
     * @return List of queues
     */
    @Override
    public List<Queue> getQueuesOfShop(long shopId) {
        return queueMapper.mapQueueList(queueRepository.findByShopId(shopId));
    }

    /**
     * Get the best available queue for a shop
     * @param shopId shop Id
     * @return Best available queue
     * @throws QueueNotFoundException when a available queue could not be found
     */
    @Override
    public Queue getBestAvailableQueue(long shopId) throws QueueNotFoundException {
        List<Queue> queues = getQueuesOfShop(shopId);
        if (!queues.isEmpty()) {
            Queue bestQueue = null;
            int prevWaitTime = -1;
            for (Queue queue : queues) {
                //Check if queue is available
                if (queue.getMaxCustomers() - queue.getQueueEntries().size() > 0) {
                    if (bestQueue == null || (prevWaitTime > queue.getQueueEntries().size() * queue.getAvgWaitTime())) {
                        bestQueue = queue;
                        prevWaitTime = queue.getQueueEntries().size() * queue.getAvgWaitTime(); //Check the minimum wait time
                    }
                }
            }
            return bestQueue;
        }
        throw new QueueNotFoundException("Available Queue Not Found");
    }

    /**
     * Create a queue entry
     * @param entry queue entry Dto
     * @return queue entry Dto
     */
    @Override
    @Transactional
    public QueueEntry createQueueEntry(QueueEntry entry) {
        QueueEntryDao entryDao = queueInverseMapper.mapQueueEntry(entry);
        return queueMapper.mapQueueEntry(queueEntryRepository.save(entryDao));
    }

    /**
     * Remove a queue entry when there is a cancellation or a completion of a queue entry
     * @param reservationId reservation Id of a queue entry
     * @throws NotFoundException when a queue entry could not be found
     */
    @Override
    @Transactional
    public void removeEntry(long reservationId) throws NotFoundException {
        if (!queueEntryRepository.findByReservationId(reservationId).isEmpty()) {
            queueEntryRepository.deleteByReservationId(reservationId);
        }
        throw new NotFoundException("Queue entry not found");
    }
}
