package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.property_type.PropertyTypeRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.property_type.PropertyTypeResponse;
import com.javaweb.batdongsan.service.PropertyTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property-types")
@RequiredArgsConstructor
public class PropertyTypeController {
    private final PropertyTypeService propertyTypeService;
    @PostMapping("/create")
    public ApiResponse<PropertyTypeResponse> create(@RequestBody @Valid PropertyTypeRequest request) {
        ApiResponse<PropertyTypeResponse> response = new ApiResponse<>();
        response.setResult(propertyTypeService.create(request));
        response.setMessage("Property type created successfully");
        return response;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<PropertyTypeResponse>> getAll() {
        ApiResponse<List<PropertyTypeResponse>> response = new ApiResponse<>();
        response.setResult(propertyTypeService.getAll());
        return response;
    }

    @GetMapping("/getById/{id}")
    public ApiResponse<PropertyTypeResponse> getById(@PathVariable Long id) {
        ApiResponse<PropertyTypeResponse> response = new ApiResponse<>();
        response.setResult(propertyTypeService.getById(id));
        return response;
    }

    @PutMapping("/update/{id}")
    public ApiResponse<PropertyTypeResponse> update(@PathVariable Long id,
                                                    @RequestBody @Valid PropertyTypeRequest request) {
        ApiResponse<PropertyTypeResponse> response = new ApiResponse<>();
        response.setResult(propertyTypeService.update(id, request));
        response.setMessage("Property type updated successfully");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        propertyTypeService.delete(id);
        response.setResult("Property type deleted successfully");
        return response;
    }
}
