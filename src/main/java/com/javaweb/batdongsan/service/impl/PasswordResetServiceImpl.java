package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.entity.PasswordReset;
import com.javaweb.batdongsan.entity.User;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.otp.EmailRequest;
import com.javaweb.batdongsan.model.request.otp.ResetRequest;
import com.javaweb.batdongsan.repository.PasswordResetRepository;
import com.javaweb.batdongsan.repository.UserRepository;
import com.javaweb.batdongsan.service.EmailService;
import com.javaweb.batdongsan.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserRepository userRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @Override
    public void sendOtp(EmailRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String otp = String.format("%06d",new Random().nextInt(999999));

        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setOtp(otp);
        passwordReset.setEmail(request.getEmail());
        passwordReset.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        passwordResetRepository.save(passwordReset);

        String to = "phuongck36@gmail.com";
        String subject = "otp xac thuc";
        String text = "ma otp cua ban la : " + otp +" . Ma nay co hieu luc 5 phut";
        emailService.sendEmail(to,subject,text);

    }

    @Override
    public void resetPassword(ResetRequest request) {
        PasswordReset passwordReset = passwordResetRepository.findByEmailAndOtp(request.getEmail(),
                request.getOtpCode())
                .orElseThrow(()-> new AppException(ErrorCode.INVALID_OTP));
        if(passwordReset.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
//        test thu , neu co gmail that thi doi thanh findByEmail()
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String password = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(password);
        userRepository.save(user);
    }
}
