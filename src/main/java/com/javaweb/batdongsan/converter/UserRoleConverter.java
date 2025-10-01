package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.UserRole;
import com.javaweb.batdongsan.model.request.user_role.UserRoleRequest;
import com.javaweb.batdongsan.model.response.user_role.UserRoleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {
    @Autowired
    ModelMapper modelMapper;

    public UserRoleResponse toResponse(UserRole userRole){
        UserRoleResponse userRoleResponse = new UserRoleResponse();

        userRoleResponse.setId(userRole.getId());
        userRoleResponse.setUserName(userRole.getUser().getUserName());
        userRoleResponse.setCode(userRole.getRole().getCode());
        userRoleResponse.setRoleName(userRole.getRole().getRoleName());
        userRoleResponse.setCreatedDate(userRole.getCreatedDate());
        userRoleResponse.setModifiedDate(userRole.getModifiedDate());

        return userRoleResponse;
    }

}
