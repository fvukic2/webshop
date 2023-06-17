package com.fvukic.webshop.user;

import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.user.model.UserRequest;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveNewUserRequest(UserRequest userRequest) {
        User user = getUserRequest(userRequest);
        userRepository.save(user);
    }

    @Override
    public void updateUserRequest(UserRequest userRequest, Integer userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,userId));
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setPassword(userRequest.getPassword());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUserRequest(Integer userId) {
        if (!userRepository.existsById(userId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,userId);
        }
        userRepository.deleteById(userId);
    }

    private User getUserRequest(UserRequest userRequest){
        return User.builder().username(userRequest.getUsername())
                .password(userRequest.getPassword()).build();
    }
}
