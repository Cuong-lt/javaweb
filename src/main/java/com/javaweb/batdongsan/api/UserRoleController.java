package com.javaweb.batdongsan.api;


import com.javaweb.batdongsan.model.request.user_role.UserRoleRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.user_role.UserRoleResponse;
import com.javaweb.batdongsan.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_roles")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/assign")
    public ApiResponse<UserRoleResponse> assignRoleToUser(@RequestBody UserRoleRequest request) {
        ApiResponse<UserRoleResponse> response = new ApiResponse<>();
        response.setResult(userRoleService.addRoleToUser(request));
        return response;
    }
    @GetMapping("/getById/{id}")
    public ApiResponse<UserRoleResponse> getById(@PathVariable Long id) {
        ApiResponse<UserRoleResponse> response = new ApiResponse<>();
        response.setResult(userRoleService.getById(id));
        return response;
    }
    @GetMapping("/getAll")
    public ApiResponse<List<UserRoleResponse>> getAll() {
        ApiResponse<List<UserRoleResponse>> response = new ApiResponse<>();
        response.setResult(userRoleService.getAll());
        return response;
    }

    @GetMapping("/getByUserName")
    public ApiResponse<List<UserRoleResponse>> getByUserName(
            @RequestParam (value = "userName", required = true) String userName ){
        ApiResponse<List<UserRoleResponse>> response = new ApiResponse<>();
        response.setResult(userRoleService.getByUserName(userName));
        return response;
    }

    @GetMapping("/getByRoleCode")
    public ApiResponse<List<UserRoleResponse>> getByRoleCode(
            @RequestParam (value = "code", required = true) String code ){
        ApiResponse<List<UserRoleResponse>> response = new ApiResponse<>();
        response.setResult(userRoleService.getByRoleCode(code));
        return response;
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserRoleResponse> updateUserRole(@PathVariable Long id,
                                                        @RequestBody UserRoleRequest request) {

        ApiResponse<UserRoleResponse> response = new ApiResponse<>();
        response.setResult(userRoleService.updateUserRoleById(id,request));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteUserRole(@PathVariable Long id) {
        userRoleService.deleteUserRoleById(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Delete successfully");
        return response;
    }
}
