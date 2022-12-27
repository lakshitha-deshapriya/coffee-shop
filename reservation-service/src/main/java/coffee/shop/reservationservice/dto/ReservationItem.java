package coffee.shop.reservationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * ReservationItem Dto
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationItem {
    private long resItemId;
    private long menuItemId;
    private int quantity;

    public long getResItemId() {
        return resItemId;
    }

    public void setResItemId(long resItemId) {
        this.resItemId = resItemId;
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationItem that = (ReservationItem) o;
        return resItemId == that.resItemId && menuItemId == that.menuItemId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resItemId, menuItemId, quantity);
    }
}
