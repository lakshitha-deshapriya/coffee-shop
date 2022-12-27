package coffee.shop.reservationservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "reservation_item")
public class ReservationItemDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "res_item_seq")
    @GenericGenerator(name = "res_item_seq", strategy="increment")
    @Column(name = "res_item_id")
    private Long resItemId;

    @Column(name = "reservation_id", insertable = false, updatable = false, nullable = false)
    private Long reservationId;

    @Column(name = "item_id", nullable = false)
    private Long menuItemId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationDao reservation;

    public Long getResItemId() {
        return resItemId;
    }

    public void setResItemId(Long resItemId) {
        this.resItemId = resItemId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ReservationDao getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDao reservation) {
        this.reservation = reservation;
    }
}
