package com.javaweb.batdongsan.model.response.property;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyResponse {
    Long id;
    String title;
    String description;
    BigDecimal price;
    Float area;// diện tích
    String address; //địa chỉ cụ thể
    String city; // thành phố nào
    String district; //Quan huyen
    String category;
    String type;
    String status;
    String email;
    String project;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
}
