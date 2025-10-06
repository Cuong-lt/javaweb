package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.PropertyCategory;
import com.javaweb.batdongsan.model.request.property_category.PropertyCategoryRequest;
import com.javaweb.batdongsan.model.response.property_category.PropertyCategoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyCategoryConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PropertyCategoryResponse toResponse(PropertyCategory entity) {
        return modelMapper.map(entity, PropertyCategoryResponse.class);
    }

    public PropertyCategory toEntity(PropertyCategoryRequest request) {
        return modelMapper.map(request, PropertyCategory.class);
    }

    public PropertyCategory toEntity(PropertyCategory entity, PropertyCategoryRequest request) {
        modelMapper.map(request, entity);
        return entity;
    }
}
