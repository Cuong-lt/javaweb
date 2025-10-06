package com.javaweb.batdongsan.model.request.property_status;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyStatusRequest {
    @NotBlank(message = "status name must not be blank")
    String name;
}
