package coffee.shop.auth.controller;

import coffee.shop.auth.dto.User;
import coffee.shop.auth.dto.UserDetails;
import coffee.shop.auth.exception.NotFoundException;
import coffee.shop.auth.exception.ValidationException;
import coffee.shop.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for user functionalities
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping(value = "/")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username) throws NotFoundException {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDetails userDetails, @RequestParam(value = "customer", required = false, defaultValue = "false") boolean customer ) throws ValidationException {
        User user = userService.createUser(userDetails, customer);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping( value = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @Valid @RequestBody UserDetails userDetails) throws NotFoundException {
        return new ResponseEntity<>( userService.updateUser( userDetails, userId ), HttpStatus.OK );
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) throws NotFoundException {
        userService.removeUser(userId);
        return ResponseEntity.noContent().build();
    }
}
