package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.otp.EmailRequest;
import com.javaweb.batdongsan.model.request.otp.ResetRequest;

public interface PasswordResetService {
    void sendOtp(EmailRequest request);
    void resetPassword(ResetRequest request);
}
