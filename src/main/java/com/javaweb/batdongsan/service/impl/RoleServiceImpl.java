package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.RoleConverter;
import com.javaweb.batdongsan.entity.Role;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.role.RoleRequest;
import com.javaweb.batdongsan.model.response.role.RoleResponse;
import com.javaweb.batdongsan.repository.RoleRepository;
import com.javaweb.batdongsan.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleConverter roleConverter;
    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleConverter.toEntity(request);
        if(roleRepository.existsByCode(role.getCode())){
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }
        roleRepository.save(role);
        return roleConverter.toResponse(role);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = roleList.stream()
                .map((role) -> roleConverter.toResponse(role))
                .toList();
        return roleResponseList;
    }

    @Override
    public RoleResponse getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        return roleConverter.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long roleId, RoleRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        role =  roleConverter.toEntity(role,request);
        roleRepository.save(role);
        return roleConverter.toResponse(role);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        roleRepository.delete(role);
    }

}
