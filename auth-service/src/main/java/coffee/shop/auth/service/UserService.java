package coffee.shop.auth.service;

import coffee.shop.auth.dto.User;
import coffee.shop.auth.dto.UserDetails;
import coffee.shop.auth.exception.NotFoundException;
import coffee.shop.auth.exception.ValidationException;


public interface UserService {
    User findUserById( int userId ) throws NotFoundException;

    User findUserByUsername( String username ) throws NotFoundException;

    User createUser(UserDetails userDetails, boolean customer) throws ValidationException;

    void removeUser(int userId) throws NotFoundException;

    User updateUser(UserDetails userDetails, int userId) throws NotFoundException;
}
