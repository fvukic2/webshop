package com.fvukic.webshop.user;

import com.fvukic.webshop.user.model.UserRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    @ApiOperation(value = "Adds new UserRequest to database", notes = "Enter all UserRequest parameters to add new UserRequest object to database", response = UserRequest.class)
    private void saveNewUserRequest(@ApiParam(value = "UserRequest value you pass to the database")@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        userService.saveNewUserRequest(userRequest);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Updates UserRequest in database", notes = "Enter UserRequest id to update UserRequest object in database", response = UserRequest.class)
    private void updateUserRequest(@ApiParam(value = "UserRequest value you pass to the database")@Valid @PathVariable Integer userId, @RequestBody UserRequest userRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        userService.updateUserRequest(userRequest,userId);
    }

    @DeleteMapping("/{userId}")
    private void deleteUserRequest(@PathVariable Integer userId){
        userService.deleteUserRequest(userId);
    }
}
