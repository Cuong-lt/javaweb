package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.role.RoleRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.role.RoleResponse;
import com.javaweb.batdongsan.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ApiResponse<RoleResponse> createRole(@RequestBody @Valid RoleRequest request){
        ApiResponse<RoleResponse> response = new ApiResponse<>();
        response.setResult(roleService.createRole(request));
        response.setMessage("Role created successfully");
        return response;
    }

    @GetMapping("/getAllRoles")
    public ApiResponse<List<RoleResponse>> getAllRoles(){
        ApiResponse<List<RoleResponse>> response = new ApiResponse<>();
        response.setResult(roleService.getAllRoles());
        return response;
    }

    @GetMapping("/getRoleById/{roleId}")
    public ApiResponse<RoleResponse> getRoleById(@PathVariable Long roleId){
        ApiResponse<RoleResponse> response = new ApiResponse<>();
        response.setResult(roleService.getRoleById(roleId));
        return response;
    }

    @PutMapping("/update/{roleId}")
    public ApiResponse<RoleResponse> updateRoleById(@PathVariable Long roleId, @RequestBody @Valid RoleRequest request){
        ApiResponse<RoleResponse> response = new ApiResponse<>();
        response.setResult(roleService.updateRole(roleId, request));
        response.setMessage("Role updated successfully");
        return response;
    }

    @DeleteMapping("/delete/{roleId}")
    public ApiResponse<String> deleteRoleById(@PathVariable Long roleId){
        ApiResponse<String> response = new ApiResponse<>();
        roleService.deleteRoleById(roleId);
        response.setMessage("Role deleted successfully");
        return response;
    }
}
