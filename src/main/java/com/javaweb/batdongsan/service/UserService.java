package com.javaweb.batdongsan.service;


import com.javaweb.batdongsan.model.request.user.RegisterRequest;
import com.javaweb.batdongsan.model.request.user.UserCreationRequest;
import com.javaweb.batdongsan.model.request.user.UserUpdateRequest;
import com.javaweb.batdongsan.model.response.user.UserResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserResponse createRequest(UserCreationRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    UserResponse updateUser(Long userId, @Valid UserUpdateRequest request);

    void deleteUser(Long userId);

    UserResponse getMyInfo();

    UserResponse register(RegisterRequest request);
}
