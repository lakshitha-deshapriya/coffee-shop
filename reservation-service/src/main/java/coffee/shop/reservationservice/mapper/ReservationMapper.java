package coffee.shop.reservationservice.mapper;

import coffee.shop.reservationservice.dao.ReservationDao;
import coffee.shop.reservationservice.dto.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Reservation Dao to Dto mapper
 */
@Mapper(componentModel = "spring", uses = {ReservationQualifier.class})
public abstract class ReservationMapper {
    @Mappings(
            @Mapping(source = "orderStatus", target = "orderStatus", qualifiedBy = ReservationQualifier.OrderStatus.class )
    )
    public abstract Reservation mapReservation(ReservationDao reservationDao);
}
