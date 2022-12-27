package coffee.shop.reservationservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class ReservationDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "res_seq")
    @GenericGenerator(name = "res_seq", strategy="increment")
    @Column(name = "reservation_id", nullable = false)
    private Long reservationId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "tax", nullable = false)
    private Double tax;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "shop_id", length = 500, nullable = false)
    private Long shopId;

    @Column(name = "order_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    @Column(name = "status", nullable = false)
    private Integer orderStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation", orphanRemoval = true)
    private List<ReservationItemDao> reservationItems;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ReservationItemDao> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(List<ReservationItemDao> reservationItems) {
        this.reservationItems = reservationItems;
    }
}
