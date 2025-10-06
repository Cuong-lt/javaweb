package com.javaweb.batdongsan.model.request.property_type;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyTypeRequest {
    @NotBlank(message = "type name must not be blanked")
    String name;
}
