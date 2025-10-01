package com.javaweb.batdongsan.model.request.user;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 6, message = "USERNAME_PASSWORD_INVALID")
    String password;
    String fullName;
    String phone;
    String email;
    String avatar;
    Integer status;
}
