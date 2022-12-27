package coffee.shop.reservationservice.service;

import coffee.shop.reservationservice.dao.ReservationDao;
import coffee.shop.reservationservice.dto.Reservation;
import coffee.shop.reservationservice.exception.NotFoundException;
import coffee.shop.reservationservice.repository.ReservationRepository;
import coffee.shop.reservationservice.util.OrderStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationServiceTest {
    @MockBean
    private ReservationRepository reservationRepository;

    @SpyBean
    private ReservationService reservationService;

    @Before
    public void before() {
        ReservationDao reservationDao = getMockReservationDao();

        when(reservationRepository.findById(20L)).thenReturn(Optional.of(reservationDao));
        when(reservationRepository.save(reservationDao)).thenReturn(reservationDao);
    }

    @Test
    public void testGetReservationById() throws NotFoundException {
        Reservation reservation = reservationService.getReservationById(20L);
        Assert.assertNotNull(reservation);
        Assert.assertEquals(20, reservation.getReservationId());
        Assert.assertEquals(1, reservation.getShopId());
        Assert.assertEquals(6.0, reservation.getTax(), 0.01);
        Assert.assertEquals(4, reservation.getCustomerId());
        Assert.assertEquals(34.2, reservation.getTotalAmount(), 0.01);
        Assert.assertEquals(OrderStatus.CONFIRMED.name(), reservation.getOrderStatus());
    }

    @Test(expected = NotFoundException.class)
    public void testNonExistingGetOrder() throws NotFoundException {
        reservationService.getReservationById(1000L);
    }

    private ReservationDao getMockReservationDao() {
        ReservationDao reservationDao = new ReservationDao();
        reservationDao.setReservationId(20L);
        reservationDao.setOrderStatus(OrderStatus.CONFIRMED.getStatus());
        reservationDao.setShopId(1L);
        reservationDao.setTax(6.0);
        reservationDao.setCustomerId(4L);
        reservationDao.setOrderTime(new Date());
        reservationDao.setTotalAmount(34.2);

        return reservationDao;
    }
}
