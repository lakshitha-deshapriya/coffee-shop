package coffee.shop.shopservice.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "menu_item" )
public class MenuItemDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "menu_item_seq")
    @GenericGenerator(name = "menu_item_seq", strategy="increment")
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "menu_id", insertable = false, updatable = false, nullable = false)
    private Long menuId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuDao menu;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public MenuDao getMenu() {
        return menu;
    }

    public void setMenu(MenuDao menu) {
        this.menu = menu;
    }
}
