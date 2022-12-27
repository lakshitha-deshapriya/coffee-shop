package coffee.shop.reservationservice.controller;

import coffee.shop.reservationservice.dto.Reservation;
import coffee.shop.reservationservice.exception.NotFoundException;
import coffee.shop.reservationservice.exception.ReservationFailedException;
import coffee.shop.reservationservice.exception.ValidationException;
import coffee.shop.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to handle reservations
 */
@RestController
@RequestMapping("reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable long reservationId) throws NotFoundException {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation body) throws ValidationException, ReservationFailedException {
        Reservation reservation = reservationService.createReservation(body);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable long reservationId) throws NotFoundException, ReservationFailedException {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{reservationId}/{status}")
    public ResponseEntity<Boolean> updateReservationStatus(@PathVariable long reservationId, @PathVariable int status) throws NotFoundException, ValidationException {
        return ResponseEntity.ok(reservationService.updateStatus(reservationId, status));
    }
}
