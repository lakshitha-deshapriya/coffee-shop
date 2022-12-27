package coffee.shop.auth.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "description")
    private String description;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId.equals(role.roleId) && code.equals(role.code) && Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, code, description);
    }
}
