package com.javaweb.batdongsan.model.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String userName;
    String password;
    String fullName;
    String phone;
    String email;
    String avatar;
    Integer status;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
//    String modifiedBy;

//    @Builder.Default
//    boolean hasPassword = true;
}
