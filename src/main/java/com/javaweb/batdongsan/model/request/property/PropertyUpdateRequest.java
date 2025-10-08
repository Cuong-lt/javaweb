package com.javaweb.batdongsan.model.request.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyUpdateRequest {
    @NotBlank(message = "title must not be blanked")
    String title;
    @NotBlank(message = "description must not be blanked")
    String description;
    @NotNull
    BigDecimal price;
    @NotNull
    Float area;// diện tích
    @NotBlank(message = "address must not be blanked")
    String address; //địa chỉ cụ thể
    String city; // thành phố nào
    String district; // Quận Huyện
    @NotBlank(message = "category must not be blanked")
    String category;
    @NotBlank(message = "type must not be blanked")
    String type;
    @NotBlank(message = "status must not be blanked")
    String status;
    @NotBlank(message = "email must not be blanked")
    String email;
    String project;
}
