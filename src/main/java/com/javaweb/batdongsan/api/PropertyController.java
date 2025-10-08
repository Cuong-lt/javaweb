package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.request.property.PropertyUpdateRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import com.javaweb.batdongsan.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("/create")
    public ApiResponse<PropertyResponse> createProperty(@RequestBody @Valid PropertyCreateRequest request){
            ApiResponse<PropertyResponse> response = new ApiResponse<>();
            response.setResult(propertyService.createProperty(request));
            response.setMessage("property created successfully");
            return response;
    }
    @GetMapping("/{id}")
    public ApiResponse<PropertyResponse> getById(@PathVariable Long id){
        ApiResponse<PropertyResponse> response = new ApiResponse<>();
        response.setResult(propertyService.getById(id));
        return response;
    }
    @GetMapping("")
    public ApiResponse<List<PropertyResponse>> getAll(){
        ApiResponse<List<PropertyResponse>> response = new ApiResponse<>();
        response.setResult(propertyService.getAll());
        return response;
    }
    @PutMapping("/{id}")
    public ApiResponse<PropertyResponse> updateById(@PathVariable Long id,
                                                    @RequestBody @Valid PropertyUpdateRequest request){
        ApiResponse<PropertyResponse> response = new ApiResponse<>();
        response.setMessage("update successfully");
        response.setResult(propertyService.update(id,request));
        return response;
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteById(@PathVariable Long id){
        ApiResponse<String> response = new ApiResponse<>();
        propertyService.deleteById(id);
        response.setMessage("delete successfully");
        return response;
    }

}
