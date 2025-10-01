package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.user.RegisterRequest;
import com.javaweb.batdongsan.model.request.user.UserCreationRequest;
import com.javaweb.batdongsan.model.request.user.UserUpdateRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.user.UserResponse;
import com.javaweb.batdongsan.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody  @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createRequest(request));
        response.setMessage("User created successfully");
        return response;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody @Valid RegisterRequest request){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.register(request));
        response.setMessage("register successfully");
        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ApiResponse<List<UserResponse>> getAllUsers(){
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getAllUsers());
        return response;
    }

    @GetMapping("/getById/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long userId){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.getUserById(userId));
        return response;
    }
    @GetMapping("/getMyInfo")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.getMyInfo());
        return response;
    }

    @PutMapping("/update/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long userId, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.updateUser(userId,request));
        response.setMessage("User updated successfully");
        return response;
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponse<String> deleteUserById(@PathVariable Long userId) {
        ApiResponse<String> response = new ApiResponse<>();
        userService.deleteUser(userId);
        response.setResult("User deleted successfully");
        return response;
    }
}
