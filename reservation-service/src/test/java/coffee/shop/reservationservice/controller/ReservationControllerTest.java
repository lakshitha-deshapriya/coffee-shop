package coffee.shop.reservationservice.controller;

import coffee.shop.reservationservice.dto.Reservation;
import coffee.shop.reservationservice.exception.NotFoundException;
import coffee.shop.reservationservice.exception.ReservationFailedException;
import coffee.shop.reservationservice.exception.ValidationException;
import coffee.shop.reservationservice.service.ReservationService;
import coffee.shop.reservationservice.util.OrderStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {
    @Autowired
    private ReservationController reservationController;

    @SpyBean
    private ReservationService reservationService;

    @Before
    public void before() throws NotFoundException, ValidationException, ReservationFailedException {
        Reservation reservation = getMockedReservation();

        doReturn(reservation).when(reservationService).getReservationById(20L);
        doReturn(reservation).when(reservationService).createReservation(reservation);
    }

    @Test
    public void testGetReservation() throws NotFoundException {

        ResponseEntity<Reservation> reservation = reservationController.getReservation(20L);
        Assert.assertNotNull(reservation);
        Assert.assertNotNull(reservation.getBody());
        Assert.assertEquals(HttpStatus.OK, reservation.getStatusCode());
    }

    @Test
    public void testCreateReservation() throws ValidationException, ReservationFailedException {

        ResponseEntity<Reservation> reservation = reservationController.createReservation(getMockedReservation());
        Assert.assertNotNull(reservation);
        Assert.assertNotNull(reservation.getBody());
        Assert.assertEquals(HttpStatus.CREATED, reservation.getStatusCode());
    }

    private Reservation getMockedReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(20L);
        reservation.setOrderStatus(OrderStatus.CONFIRMED.name());
        reservation.setShopId(1L);
        reservation.setTax(6.0);
        reservation.setCustomerId(4L);
        reservation.setOrderTime(new Date().toString());
        reservation.setTotalAmount(34.2);

        return reservation;
    }
}
