package com.javaweb.batdongsan.model.response.property_status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyStatusResponse {
    Long id;
    String name;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
}
