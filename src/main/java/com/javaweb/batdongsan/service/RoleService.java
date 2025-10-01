package com.javaweb.batdongsan.service;


import com.javaweb.batdongsan.model.request.role.RoleRequest;
import com.javaweb.batdongsan.model.response.role.RoleResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(@Valid RoleRequest request);

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long roleId);

    RoleResponse updateRole(Long roleId, @Valid RoleRequest request);

    void deleteRoleById(Long roleId);
}
