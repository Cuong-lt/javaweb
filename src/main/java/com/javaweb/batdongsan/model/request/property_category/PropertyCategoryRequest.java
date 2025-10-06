package com.javaweb.batdongsan.model.request.property_category;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyCategoryRequest {
    @NotBlank(message = "category name must not be blank")
    String name;
}
