package coffee.shop.shopservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shop" )
public class ShopDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "shop_seq")
    @GenericGenerator(name = "shop_seq", strategy="increment")
    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "contactNo", length = 15)
    private String contactNo;

    @Column(name = "no_of_queues")
    private Integer noOfQueues;

    @Column(name = "queue_size")
    private Integer queueSize;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop", orphanRemoval = true)
    private List<MenuDao> menus;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
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

    public Integer getNoOfQueues() {
        return noOfQueues;
    }

    public void setNoOfQueues(Integer noOfQueues) {
        this.noOfQueues = noOfQueues;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<MenuDao> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDao> menus) {
        this.menus = menus;
    }
}
