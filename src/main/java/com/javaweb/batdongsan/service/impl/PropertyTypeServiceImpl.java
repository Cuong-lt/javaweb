package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.PropertyTypeConverter;
import com.javaweb.batdongsan.entity.PropertyType;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property_type.PropertyTypeRequest;
import com.javaweb.batdongsan.model.response.property_type.PropertyTypeResponse;
import com.javaweb.batdongsan.repository.PropertyTypeRepository;
import com.javaweb.batdongsan.service.ActivityLogService;
import com.javaweb.batdongsan.service.PropertyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyTypeServiceImpl implements PropertyTypeService {
    private final PropertyTypeRepository propertyTypeRepository;
    private final PropertyTypeConverter propertyTypeConverter;
    private final ActivityLogService activityLogService;

    @Override
    public PropertyTypeResponse create(PropertyTypeRequest request) {
        if (propertyTypeRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.TYPE_EXISTED);
        }
        PropertyType entity = propertyTypeConverter.toEntity(request);
        propertyTypeRepository.save(entity);
        activityLogService.log("CREATE ", "ADMIN",
                "Tạo loại bất động sản: " + entity.getName(), "PropertyType");
        return propertyTypeConverter.toResponse(entity);
    }

    @Override
    public List<PropertyTypeResponse> getAll() {
        return propertyTypeRepository.findAll()
                .stream()
                .map(propertyTypeConverter::toResponse)
                .toList();
    }

    @Override
    public PropertyTypeResponse getById(Long id) {
        PropertyType entity = propertyTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_FOUND));
        return propertyTypeConverter.toResponse(entity);
    }

    @Override
    public PropertyTypeResponse update(Long id, PropertyTypeRequest request) {
        PropertyType entity = propertyTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_FOUND));
        entity = propertyTypeConverter.toEntity(entity, request);
        propertyTypeRepository.save(entity);
        activityLogService.log("UPDATE ", "ADMIN",
                "Cập nhật loại bất động sản: " + entity.getName(), "PropertyType");
        return propertyTypeConverter.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        PropertyType entity = propertyTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_FOUND));
        propertyTypeRepository.delete(entity);
        activityLogService.log("DELETE ", "ADMIN",
                "Xóa loại bất động sản: " + entity.getName(), "PropertyType");
    }
}
