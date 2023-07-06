package com.fvukic.webshop.user;

import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.user.model.UserRequest;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();
        logger.info("Fetched {} users", users.size());
        return users;
    }

    @Override
    public void saveNewUserRequest(UserRequest userRequest) {
        logger.info("Saving new user request");
        User user = getUserRequest(userRequest);
        userRepository.save(user);
        logger.info("Saved new user request");
    }

    @Override
    public void updateUserRequest(UserRequest userRequest, Integer userId) {
        logger.info("Updating user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating user: User with ID {} not found", userId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,userId);
                });
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setRole(userRequest.getRole());
        userRepository.save(existingUser);
        logger.info("Updated user with ID: {}", userId);
    }

    @Override
    public void deleteUserRequest(Integer userId) {
        logger.info("Deleting user with ID: {}", userId);
        if (!userRepository.existsById(userId)){
            logger.error("Error occurred while deleting user: User with ID {} not found", userId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,userId);
        }
        userRepository.deleteById(userId);
        logger.info("Deleted user with ID: {}", userId);
    }

    private User getUserRequest(UserRequest userRequest){
        logger.info("Getting user from request");
        User user = User.builder().username(userRequest.getUsername())
                .role(userRequest.getRole())
                .password(userRequest.getPassword()).build();
        logger.info("Got user from request");
        return user;
    }
}
