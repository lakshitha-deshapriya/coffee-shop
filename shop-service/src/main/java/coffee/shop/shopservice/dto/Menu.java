package coffee.shop.shopservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {
    private int menuId;
    private String name;
    private String description;
    private boolean active;
    private String lastModified;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuItem> menuItems;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menuId == menu.menuId && active == menu.active && Objects.equals(name, menu.name) && Objects.equals(description, menu.description) && Objects.equals(lastModified, menu.lastModified) && Objects.equals(menuItems, menu.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, name, description, active, lastModified, menuItems);
    }
}
