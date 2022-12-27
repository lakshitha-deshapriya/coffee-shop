package coffee.shop.auth.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name="user_id")
public class Customer extends User{

    @Column(name = "address", length = 500)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }
}
