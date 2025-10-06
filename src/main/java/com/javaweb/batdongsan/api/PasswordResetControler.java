package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.otp.EmailRequest;
import com.javaweb.batdongsan.model.request.otp.ResetRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.service.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PasswordResetControler {
    private final PasswordResetService passwordResetService;
    @PostMapping("/otp")
    public ApiResponse<String> forgotPassword(@RequestBody @Valid EmailRequest request) {
        passwordResetService.sendOtp(request);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("OTP đã được gửi đến gmail của bạn");
        return response;
    }
    @PostMapping("/reset-password")
    public ApiResponse<String> resetPassword(@RequestBody @Valid ResetRequest request) {
        passwordResetService.resetPassword(request);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Đổi mật khẩu thành công");
        return response;
    }

}
