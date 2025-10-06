package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.property_category.PropertyCategoryRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.property_category.PropertyCategoryResponse;
import com.javaweb.batdongsan.service.PropertyCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property-categories")
@RequiredArgsConstructor
public class PropertyCategoryController {
    private final PropertyCategoryService propertyCategoryService;

    @PostMapping("/create")
    public ApiResponse<PropertyCategoryResponse> create(@RequestBody @Valid PropertyCategoryRequest request) {
        ApiResponse<PropertyCategoryResponse> response = new ApiResponse<>();
        response.setResult(propertyCategoryService.create(request));
        response.setMessage("Category created successfully");
        return response;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<PropertyCategoryResponse>> getAll() {
        ApiResponse<List<PropertyCategoryResponse>> response = new ApiResponse<>();
        response.setResult(propertyCategoryService.getAll());
        return response;
    }

    @GetMapping("/getById/{id}")
    public ApiResponse<PropertyCategoryResponse> getById(@PathVariable Long id) {
        ApiResponse<PropertyCategoryResponse> response = new ApiResponse<>();
        response.setResult(propertyCategoryService.getById(id));
        return response;
    }

    @PutMapping("/update/{id}")
    public ApiResponse<PropertyCategoryResponse> update(@PathVariable Long id,
                                                        @RequestBody @Valid PropertyCategoryRequest request) {
        ApiResponse<PropertyCategoryResponse> response = new ApiResponse<>();
        response.setResult(propertyCategoryService.update(id, request));
        response.setMessage("Category updated successfully");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        propertyCategoryService.delete(id);
        response.setResult("Category deleted successfully");
        return response;
    }
}
