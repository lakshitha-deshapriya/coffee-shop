package coffee.shop.reservationservice.mapper;

import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reservation Qualifier for mapping specific functionalities during mapping
 */
@Component
public class ReservationQualifier {
    @ReservationQualifier.OrderStatus
    public String getOrderStatus(Integer status) {
        return coffee.shop.reservationservice.util.OrderStatus.getOrderStatus(status);
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface OrderStatus {
    }
}
