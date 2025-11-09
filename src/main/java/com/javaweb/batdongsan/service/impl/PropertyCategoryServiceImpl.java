package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.PropertyCategoryConverter;
import com.javaweb.batdongsan.entity.PropertyCategory;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property_category.PropertyCategoryRequest;
import com.javaweb.batdongsan.model.response.property_category.PropertyCategoryResponse;
import com.javaweb.batdongsan.repository.PropertyCategoryRepository;
import com.javaweb.batdongsan.service.ActivityLogService;
import com.javaweb.batdongsan.service.PropertyCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyCategoryServiceImpl implements PropertyCategoryService {
    private final PropertyCategoryRepository propertyCategoryRepository;
    private final PropertyCategoryConverter propertyCategoryConverter;
    private final ActivityLogService activityLogService;

    @Override
    public PropertyCategoryResponse create(PropertyCategoryRequest request) {
        if (propertyCategoryRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        PropertyCategory entity = propertyCategoryConverter.toEntity(request);
        propertyCategoryRepository.save(entity);
        activityLogService.log("CREATE ", "ADMIN",
                "Tạo danh mục bất động sản: " + entity.getName(), "PropertyCategory");
        return propertyCategoryConverter.toResponse(entity);
    }

    @Override
    public List<PropertyCategoryResponse> getAll() {
        return propertyCategoryRepository.findAll()
                .stream()
                .map(propertyCategoryConverter::toResponse)
                .toList();
    }

    @Override
    public PropertyCategoryResponse getById(Long id) {
        PropertyCategory entity = propertyCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return propertyCategoryConverter.toResponse(entity);
    }

    @Override
    public PropertyCategoryResponse update(Long id, PropertyCategoryRequest request) {
        PropertyCategory entity = propertyCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        entity = propertyCategoryConverter.toEntity(entity, request);
        propertyCategoryRepository.save(entity);
        activityLogService.log("UPDATE ", "ADMIN",
                "Cập nhật danh mục bất động sản: " + entity.getName(), "PropertyCategory");
        return propertyCategoryConverter.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        PropertyCategory entity = propertyCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        propertyCategoryRepository.delete(entity);
        activityLogService.log("DELETE ", "ADMIN",
                "Xóa danh mục bất động sản: " + entity.getName(), "PropertyCategory");
    }
}
