package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.*;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import com.javaweb.batdongsan.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyStatusRepository propertyStatusRepository;
    @Autowired
    private PropertyTypeRepository propertyTypeRepository;
    @Autowired
    private PropertyCategoryRepository propertyCategoryRepository;

//    create
    public Property toEntity(PropertyCreateRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PropertyStatus status = propertyStatusRepository.findByName(request.getStatus())
                .orElseThrow(() -> new AppException(ErrorCode.STATUS_NOT_FOUND));
        PropertyType type = propertyTypeRepository.findByName(request.getType())
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_FOUND));
        PropertyCategory category = propertyCategoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Property property = modelMapper.map(request,Property.class);
        property.setUser(user);
        property.setCategory(category);
        property.setType(type);
        property.setStatus(status);

        return property;
    }

    public PropertyResponse toResponse(Property property){
        PropertyResponse response = modelMapper.map(property,PropertyResponse.class);
        response.setEmail(property.getUser().getEmail());
        response.setCategory(property.getCategory().getName());
        response.setType(property.getType().getName());
        response.setStatus(property.getStatus().getName());
        return response;
    }
}
