package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.PropertyConverter;
import com.javaweb.batdongsan.entity.Property;
import com.javaweb.batdongsan.entity.User;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.request.property.PropertyUpdateRequest;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import com.javaweb.batdongsan.repository.*;
import com.javaweb.batdongsan.service.ActivityLogService;
import com.javaweb.batdongsan.service.PropertyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyServiceImpl implements PropertyService {

    PropertyConverter converter;
    PropertyRepository propertyRepository;
    ActivityLogService activityLogService;
    UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public PropertyResponse createProperty(PropertyCreateRequest request) {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Property property = converter.toEntity(request);
        if(propertyRepository.existsByAddress(property.getAddress())){
            throw new AppException(ErrorCode.PROPERTY_EXISTED);
        }
        propertyRepository.save(property);
        activityLogService.log("CREATE ", user.getEmail(),
                "Tạo bất động sản: " + property.getTitle(),"Property");
        return converter.toResponse(property);
    }

    @Override
    public PropertyResponse getById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        PropertyResponse response = converter.toResponse(property);
        return response;
    }

    @Override
    public List<PropertyResponse> getAll() {
        List<Property> properties = propertyRepository.findAll();
        List<PropertyResponse> propertyResponseList = properties.stream()
                .map((property) -> converter.toResponse(property))
                .toList();
        return propertyResponseList;
    }

    @Override
    public PropertyResponse update(Long id, PropertyUpdateRequest request) {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        property = converter.toEntity(property,request);
        propertyRepository.save(property);
        activityLogService.log("UPDATE ", user.getEmail(),
                "Cập nhật bất động sản: " + property.getTitle(),"Property");
        return converter.toResponse(property);
    }

    @Override
    public Void deleteById(Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        propertyRepository.deleteById(id);
        activityLogService.log("DELETE ", user.getEmail(),
                "Xóa bất động sản: " + property.getTitle(),"Property");
        return null;
    }



}
