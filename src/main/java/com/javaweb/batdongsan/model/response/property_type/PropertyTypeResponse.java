package com.javaweb.batdongsan.model.response.property_type;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyTypeResponse {
    Long id;
    String name;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
}
