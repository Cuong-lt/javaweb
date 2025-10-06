package com.javaweb.batdongsan.model.request.otp;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetRequest {
    @NotBlank
    String email;

    @NotBlank
    String otpCode;

    @NotBlank
    String newPassword;
}
