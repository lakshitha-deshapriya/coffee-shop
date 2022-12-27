package coffee.shop.reservationservice.mapper;

import coffee.shop.reservationservice.dao.ReservationDao;
import coffee.shop.reservationservice.dto.Reservation;
import org.mapstruct.Mapper;

/**
 * Reservation Dto to Dao mapper
 */
@Mapper(componentModel = "spring")
public abstract class ReservationInverseMapper {
    public abstract ReservationDao mapReservation(Reservation reservation);
}
