package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.PropertyType;
import com.javaweb.batdongsan.model.request.property_type.PropertyTypeRequest;
import com.javaweb.batdongsan.model.response.property_type.PropertyTypeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyTypeConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PropertyTypeResponse toResponse(PropertyType entity) {
        return modelMapper.map(entity, PropertyTypeResponse.class);
    }

    public PropertyType toEntity(PropertyTypeRequest request) {
        return modelMapper.map(request, PropertyType.class);
    }

    public PropertyType toEntity(PropertyType entity, PropertyTypeRequest request) {
        modelMapper.map(request, entity);
        return entity;
    }
}
