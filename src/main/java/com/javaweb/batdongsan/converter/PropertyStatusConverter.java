package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.PropertyStatus;
import com.javaweb.batdongsan.model.request.property_status.PropertyStatusRequest;
import com.javaweb.batdongsan.model.response.property_status.PropertyStatusResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyStatusConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PropertyStatusResponse toResponse(PropertyStatus entity) {
        return modelMapper.map(entity, PropertyStatusResponse.class);
    }

    public PropertyStatus toEntity(PropertyStatusRequest request) {
        return modelMapper.map(request, PropertyStatus.class);
    }

    public PropertyStatus toEntity(PropertyStatus entity, PropertyStatusRequest request) {
        modelMapper.map(request, entity);
        return entity;
    }
}
