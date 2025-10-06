package com.javaweb.batdongsan.model.response.property_category;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyCategoryResponse {
    Long id;
    String name;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
}
