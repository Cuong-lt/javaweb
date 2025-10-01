package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.UserRoleConverter;
import com.javaweb.batdongsan.entity.Role;
import com.javaweb.batdongsan.entity.User;
import com.javaweb.batdongsan.entity.UserRole;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.user_role.UserRoleRequest;
import com.javaweb.batdongsan.model.response.user_role.UserRoleResponse;
import com.javaweb.batdongsan.repository.RoleRepository;
import com.javaweb.batdongsan.repository.UserRepository;
import com.javaweb.batdongsan.repository.UserRoleRepository;
import com.javaweb.batdongsan.service.UserRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UserRoleConverter userRoleConverter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserRoleResponse addRoleToUser(UserRoleRequest request) {
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Role role = roleRepository.findByCode(request.getCode())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRole = userRoleRepository.save(userRole);
        return userRoleConverter.toResponse(userRole);
    }

    @Override
    public UserRoleResponse getById(Long id) {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ROLE_NOT_FOUND));
        return userRoleConverter.toResponse(userRole);
    }

    @Override
    public List<UserRoleResponse> getAll() {
        List<UserRole> userRoleList = userRoleRepository.findAll();
        List<UserRoleResponse> userRoleResponseList = userRoleList.stream()
                .map((userRole) -> userRoleConverter.toResponse(userRole))
                .toList();

        return userRoleResponseList;
    }

    @Override
    public UserRoleResponse updateUserRoleById(Long id, UserRoleRequest request) {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ROLE_NOT_FOUND));
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Role role = roleRepository.findByCode(request.getCode())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepository.save(userRole);
        return userRoleConverter.toResponse(userRole);
    }

    @Override
    public void deleteUserRoleById(Long id) {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ROLE_NOT_FOUND));
        userRoleRepository.delete(userRole);
    }

    @Override
    public List<UserRoleResponse> getByUserName(String userName) {
        List<UserRole> userRoleList = userRoleRepository.findByUser_UserName(userName);
        List<UserRoleResponse> userRoleResponseList = userRoleList.stream()
                .map((userRole) -> userRoleConverter.toResponse(userRole))
                .toList();
        return userRoleResponseList;
    }

    @Override
    public List<UserRoleResponse> getByRoleCode(String code) {
        List<UserRole> userRoleList = userRoleRepository.findByRole_Code(code);
        List<UserRoleResponse> userRoleResponseList = userRoleList.stream()
                .map((userRole) -> userRoleConverter.toResponse(userRole))
                .toList();
        return userRoleResponseList;
    }
}
