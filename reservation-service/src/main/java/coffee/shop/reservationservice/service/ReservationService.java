package coffee.shop.reservationservice.service;

import coffee.shop.reservationservice.dto.Reservation;
import coffee.shop.reservationservice.exception.NotFoundException;
import coffee.shop.reservationservice.exception.ReservationFailedException;
import coffee.shop.reservationservice.exception.ValidationException;

public interface ReservationService {
    Reservation getReservationById(long reservationId) throws NotFoundException;

    Reservation createReservation(Reservation body) throws ValidationException, ReservationFailedException;

    boolean updateStatus(long reservationId, int status) throws NotFoundException, ValidationException, ReservationFailedException;

    void cancelReservation(long reservationId) throws NotFoundException, ReservationFailedException;
}
