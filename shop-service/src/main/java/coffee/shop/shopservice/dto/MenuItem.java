package coffee.shop.shopservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {
    private int itemId;
    private String name;
    private double price;
    private boolean available;
    private String lastModified;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return itemId == menuItem.itemId && Double.compare(menuItem.price, price) == 0 && available == menuItem.available && Objects.equals(name, menuItem.name) && Objects.equals(lastModified, menuItem.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, price, available, lastModified);
    }
}
