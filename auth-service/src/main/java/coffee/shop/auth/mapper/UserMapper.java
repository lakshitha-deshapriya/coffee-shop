package coffee.shop.auth.mapper;

import coffee.shop.auth.dao.Customer;
import coffee.shop.auth.dao.UserRole;
import coffee.shop.auth.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

/**
 * User mapping from Dao object to Dto Object
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mappings(
            @Mapping(target = "roles", source = "userRoles", qualifiedByName = "mapRoles")
    )
    public abstract User mapUser(coffee.shop.auth.dao.User userDao);

    @Named("mapRoles")
    public abstract List<String> mapRoles(List<UserRole> roles);

    public String mapRole(UserRole role){
        return role.getRole().getCode();
    }

    @Mappings(
            @Mapping(target = "roles", source = "userRoles", qualifiedByName = "mapRoles")
    )
    public abstract User mapCustomer(Customer customer);
}
