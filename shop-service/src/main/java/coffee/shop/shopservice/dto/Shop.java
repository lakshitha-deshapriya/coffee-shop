package coffee.shop.shopservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shop {
    private int shopId;
    private String name;
    private double longitude;
    private double latitude;
    private String address;
    private String contactNo;
    private String noOfQueues;
    private String queueSize;
    private String openTime;
    private String closeTime;
    private String lastModified;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Menu> menus;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNoOfQueues() {
        return noOfQueues;
    }

    public void setNoOfQueues(String noOfQueues) {
        this.noOfQueues = noOfQueues;
    }

    public String getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(String queueSize) {
        this.queueSize = queueSize;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return shopId == shop.shopId && Double.compare(shop.longitude, longitude) == 0 && Double.compare(shop.latitude, latitude) == 0 && Objects.equals(name, shop.name) && Objects.equals(address, shop.address) && Objects.equals(contactNo, shop.contactNo) && Objects.equals(noOfQueues, shop.noOfQueues) && Objects.equals(queueSize, shop.queueSize) && Objects.equals(openTime, shop.openTime) && Objects.equals(closeTime, shop.closeTime) && Objects.equals(lastModified, shop.lastModified) && Objects.equals(menus, shop.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopId, name, longitude, latitude, address, contactNo, noOfQueues, queueSize, openTime, closeTime, lastModified, menus);
    }
}
