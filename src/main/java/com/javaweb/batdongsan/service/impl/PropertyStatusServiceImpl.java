package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.PropertyStatusConverter;
import com.javaweb.batdongsan.entity.PropertyStatus;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property_status.PropertyStatusRequest;
import com.javaweb.batdongsan.model.response.property_status.PropertyStatusResponse;
import com.javaweb.batdongsan.repository.PropertyStatusRepository;
import com.javaweb.batdongsan.service.ActivityLogService;
import com.javaweb.batdongsan.service.PropertyStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyStatusServiceImpl implements PropertyStatusService {
    private final PropertyStatusRepository propertyStatusRepository;
    private final PropertyStatusConverter propertyStatusConverter;
    private final ActivityLogService activityLogService;

    @Override
    public PropertyStatusResponse create(PropertyStatusRequest request) {
        if (propertyStatusRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.STATUS_EXISTED);
        }
        PropertyStatus entity = propertyStatusConverter.toEntity(request);
        propertyStatusRepository.save(entity);
        activityLogService.log("CREATE ", "ADMIN",
                "Tạo trạng thái bất động sản: " + entity.getName(), "PropertyStatus");
        return propertyStatusConverter.toResponse(entity);
    }

    @Override
    public List<PropertyStatusResponse> getAll() {
        return propertyStatusRepository.findAll()
                .stream()
                .map(propertyStatusConverter::toResponse)
                .toList();
    }

    @Override
    public PropertyStatusResponse getById(Long id) {
        PropertyStatus entity = propertyStatusRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STATUS_NOT_FOUND));
        return propertyStatusConverter.toResponse(entity);
    }

    @Override
    public PropertyStatusResponse update(Long id, PropertyStatusRequest request) {
        PropertyStatus entity = propertyStatusRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STATUS_NOT_FOUND));
        entity = propertyStatusConverter.toEntity(entity, request);
        propertyStatusRepository.save(entity);
        activityLogService.log("UPDATE ", "ADMIN",
                "Cập nhật trạng thái bất động sản: " + entity.getName(), "PropertyStatus");
        return propertyStatusConverter.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        PropertyStatus entity = propertyStatusRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STATUS_NOT_FOUND));
        propertyStatusRepository.delete(entity);
        activityLogService.log("DELETE ", "ADMIN",
                "Xóa trạng thái bất động sản: " + entity.getName(), "PropertyStatus");
    }
}
