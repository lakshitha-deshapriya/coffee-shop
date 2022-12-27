package coffee.shop.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

/**
 * Reservation Dto
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation {
    private long reservationId;
    private long customerId;
    private double tax;
    private double totalAmount;
    private long shopId;
    private String orderTime;
    private String orderStatus;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ReservationItem> reservationItems;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(List<ReservationItem> reservationItems) {
        this.reservationItems = reservationItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && customerId == that.customerId && Double.compare(that.tax, tax) == 0 && Double.compare(that.totalAmount, totalAmount) == 0 && shopId == that.shopId && Objects.equals(orderTime, that.orderTime) && Objects.equals(orderStatus, that.orderStatus) && Objects.equals(reservationItems, that.reservationItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, customerId, tax, totalAmount, shopId, orderTime, orderStatus, reservationItems);
    }
}
