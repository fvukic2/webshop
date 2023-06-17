package com.fvukic.webshop.user;

import com.fvukic.webshop.user.model.UserRequest;
import org.springframework.web.bind.annotation.*;

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
    private void saveNewUserRequest(@RequestBody UserRequest userRequest){
        userService.saveNewUserRequest(userRequest);
    }

    @PutMapping("/{userId}")
    private void updateUserRequest(@RequestBody UserRequest userRequest,@PathVariable Integer userId){
        userService.updateUserRequest(userRequest,userId);
    }

    @DeleteMapping("/{userId}")
    private void deleteUserRequest(@PathVariable Integer userId){
        userService.deleteUserRequest(userId);
    }
}
