package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.Role;
import com.javaweb.batdongsan.model.request.role.RoleRequest;
import com.javaweb.batdongsan.model.response.role.RoleResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RoleResponse toResponse(Role role){
        RoleResponse roleResponse = modelMapper.map(role, RoleResponse.class);
        return roleResponse;
    }

//    update role
    public Role toEntity(Role role, RoleRequest request){
        modelMapper.map(request, role);
        return role;
    }

//    new role
    public  Role toEntity(RoleRequest request){
        Role role = new Role();
        modelMapper.map(request,role);
        return role;
    }
}
