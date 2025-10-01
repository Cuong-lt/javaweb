package com.javaweb.batdongsan.model.response.role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    Long id;
    String roleName;
    String code;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
//    String modifiedBy;
}
