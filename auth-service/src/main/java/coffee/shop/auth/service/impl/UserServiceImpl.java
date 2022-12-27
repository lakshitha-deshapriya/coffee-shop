package coffee.shop.auth.service.impl;

import coffee.shop.auth.dao.Customer;
import coffee.shop.auth.dto.User;
import coffee.shop.auth.dto.UserDetails;
import coffee.shop.auth.exception.NotFoundException;
import coffee.shop.auth.exception.ValidationException;
import coffee.shop.auth.mapper.UserInverseMapper;
import coffee.shop.auth.mapper.UserMapper;
import coffee.shop.auth.repository.CustomerRepository;
import coffee.shop.auth.repository.UserRepository;
import coffee.shop.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service bean to handle user related functionalities
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInverseMapper userInverseMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Find a user by userId
     *
     * @param userId id of the user
     * @return User DTO
     * @throws NotFoundException if user not found
     */
    @Override
    public User findUserById(int userId) throws NotFoundException {
        coffee.shop.auth.dao.User userDao = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return mapUserDto(userDao);
    }

    /**
     * Find a user using username
     *
     * @param username username of the user
     * @return User DTO
     * @throws NotFoundException if user not found for username
     */
    @Override
    public User findUserByUsername(String username) throws NotFoundException {
        coffee.shop.auth.dao.User userDao = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        return mapUserDto(userDao);
    }

    /**
     * Create a user
     *
     * @param userDetails DTO object to get user details
     * @param customer    is the user a customer or not
     * @return User DTO
     * @throws ValidationException if data is invalid
     */
    @Override
    @Transactional
    public User createUser(UserDetails userDetails, boolean customer) throws ValidationException {
        if (customer) {
            userDetails.setUsername(userDetails.getMobileNo());
        }
        if (userRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            throw new ValidationException("Username already exist");
        }
        if (customer) {
            Customer customerDao = userInverseMapper.mapCustomer(userDetails);
            return saveCustomer(customerDao);
        }
        coffee.shop.auth.dao.User userDao = userInverseMapper.mapUser(userDetails);
        return saveUser(userDao);
    }

    /**
     * Update a created user
     *
     * @param userDetails DTO object to get user details
     * @param userId      updating users user Id
     * @return User DTO
     * @throws NotFoundException if the user is not found
     */
    @Override
    public User updateUser(UserDetails userDetails, int userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }
        coffee.shop.auth.dao.User userDao = userInverseMapper.mapUser(userDetails);
        userDao.setUserId(userId);
        return saveUser(userDao);
    }

    /**
     * Remove a user
     *
     * @param userId user Id of the user
     * @throws NotFoundException if the user was not founded
     */
    @Override
    public void removeUser(int userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    /**
     * Map a Dao User to Dto User
     *
     * @param userDao database user Object
     * @return User DTO
     */
    private User mapUserDto(coffee.shop.auth.dao.User userDao) {
        if (userDao instanceof Customer) {
            return userMapper.mapCustomer((Customer) userDao);
        }
        return userMapper.mapUser(userDao);
    }

    /**
     * Save an created user
     *
     * @param userDao database user object
     * @return User DTO
     */
    private User saveUser(coffee.shop.auth.dao.User userDao) {
        userDao.setPassword(passwordEncoder.encode(userDao.getPassword()));
        if (userDao.getUserRoles() != null) {
            userDao.getUserRoles().forEach(role -> role.setUser(userDao));
        }
        return mapUserDto(userRepository.save(userDao));
    }

    /**
     * Save a customer with User Dao
     *
     * @param customer customer dao
     * @return User DTO
     */
    private User saveCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        if (customer.getUserRoles() != null) {
            customer.getUserRoles().forEach(role -> role.setUser(customer));
        }
        return mapUserDto(customerRepository.save(customer));
    }
}
