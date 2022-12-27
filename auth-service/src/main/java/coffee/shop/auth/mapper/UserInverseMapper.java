package coffee.shop.auth.mapper;

import coffee.shop.auth.dao.Customer;
import coffee.shop.auth.dao.Role;
import coffee.shop.auth.dao.UserRole;
import coffee.shop.auth.dto.User;
import coffee.shop.auth.dto.UserDetails;
import coffee.shop.auth.exception.NotFoundException;
import coffee.shop.auth.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User mapping of User Dto Object to Dao Object
 */
@Mapper(componentModel = "spring")
public abstract class UserInverseMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Mappings(
            @Mapping(target = "userRoles", source = "roles", qualifiedByName = "mapRoles")
    )
    public abstract coffee.shop.auth.dao.User mapUser(UserDetails userDetails);

    @Named("mapRoles")
    public abstract List<UserRole> mapRoles(List<String> roles);

    public UserRole mapRole(String role) throws NotFoundException {
        UserRole userRole = new UserRole();

        Role roleDao = roleRepository.findByCode(role).orElseThrow(() -> new NotFoundException("Role not found"));
        userRole.setRole( roleDao );

        return userRole;
    }

    @Mappings(
            @Mapping(target = "userRoles", source = "roles", qualifiedByName = "mapRoles")
    )
    public abstract Customer mapCustomer(UserDetails userDetails);
}
