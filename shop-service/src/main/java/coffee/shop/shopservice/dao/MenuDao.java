package coffee.shop.shopservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu" )
public class MenuDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "menu_seq")
    @GenericGenerator(name = "menu_seq", strategy="increment")
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "shop_id", insertable = false, updatable = false, nullable = false)
    private Long shopId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopDao shop;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menu", orphanRemoval = true)
    private List<MenuItemDao> menuItems;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public ShopDao getShop() {
        return shop;
    }

    public void setShop(ShopDao shop) {
        this.shop = shop;
    }

    public List<MenuItemDao> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDao> menuItems) {
        this.menuItems = menuItems;
    }
}
