package edu.mohamed.restaurantfinder.services;

import java.util.List;
import java.util.Optional;

import edu.mohamed.restaurantfinder.model.User;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    Optional<User> updateUser(Long id, User user);
    boolean deleteUser(Long id);
}
