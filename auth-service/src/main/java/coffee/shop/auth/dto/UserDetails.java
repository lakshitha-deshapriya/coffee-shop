package coffee.shop.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * User details body for user creation
 */
public class UserDetails {

    @NotNull(message = "username is missing")
    @NotBlank(message = "username is blank")
    private String username;

    @NotNull(message = "name is missing")
    @NotBlank(message = "name is blank")
    private String name;

    @NotNull(message = "password is missing")
    @NotBlank(message = "password is blank")
    private String password;

    @NotNull(message = "mobileNo is missing")
    @NotBlank(message = "mobileNo is blank")
    private String mobileNo;

    private String address;

    @NotNull(message = "roles is missing")
    @NotBlank(message = "roles is blank")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
