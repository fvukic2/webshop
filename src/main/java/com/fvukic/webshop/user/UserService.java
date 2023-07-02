package com.fvukic.webshop.user;

import com.fvukic.webshop.user.model.UserRequest;

import java.util.List;

public interface UserService {

     List<User> getAllUsers();

     void saveNewUserRequest(UserRequest userRequest);

     void updateUserRequest(UserRequest userRequest, Integer userId);

     void deleteUserRequest(Integer userId);
}
