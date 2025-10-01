package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.entity.InvalidatedToken;
import com.javaweb.batdongsan.entity.User;
import com.javaweb.batdongsan.entity.UserRole;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.authentication.AuthenticationRequest;
import com.javaweb.batdongsan.model.request.authentication.IntrospectRequest;
import com.javaweb.batdongsan.model.request.authentication.LogOutRequest;
import com.javaweb.batdongsan.model.request.authentication.RefreshRequest;
import com.javaweb.batdongsan.model.response.authentication.AuthenticationResponse;
import com.javaweb.batdongsan.model.response.authentication.IntrospectResponse;
import com.javaweb.batdongsan.model.response.authentication.RefreshResponse;
import com.javaweb.batdongsan.repository.InvalidatedTokenRepository;
import com.javaweb.batdongsan.repository.UserRepository;
import com.javaweb.batdongsan.service.AuthenticationService;
import com.javaweb.batdongsan.service.TokenService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationImpl implements AuthenticationService {

    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    log-in
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!result){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = tokenService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAuthenticated(result);
        authenticationResponse.setToken(token);
        return authenticationResponse;
    }

//    log-out
    @Override
    public Void logout(LogOutRequest request){
        var signedToken = tokenService.verifyToken(request.getToken());
        try {
            String jit = signedToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedToken.getJWTClaimsSet().getExpirationTime();
            InvalidatedToken invalidatedToken = new InvalidatedToken();
            invalidatedToken.setId(jit);
            invalidatedToken.setExpiryTime(expiryTime);
            invalidatedTokenRepository.save(invalidatedToken);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    return null;
    }

    @Override
    public RefreshResponse refreshToken(RefreshRequest request) {
        String token = request.getToken();
        SignedJWT signedJWT = tokenService.verifyToken(token);

        try{
            //        xoa token cu~ bang cach log-out
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = new InvalidatedToken();
        invalidatedToken.setId(jit);
        invalidatedToken.setExpiryTime(expiryTime);
        invalidatedTokenRepository.save(invalidatedToken);

//        tao token moi bang userName
        String userName = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUserName(userName)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String refreshToken = tokenService.generateToken(user);

        return RefreshResponse.builder()
                    .token(refreshToken)
                    .build();
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }

    }

    //    kiem tra token.
    @Override
    public IntrospectResponse validToken(IntrospectRequest request) {
            String token = request.getToken();
            boolean isValid = true;
            IntrospectResponse introspectResponse = new IntrospectResponse();
            try {
                tokenService.verifyToken(token);
            } catch (AppException e){
                isValid = false;
            }
        introspectResponse.setValid(isValid);
            return introspectResponse;
    }



}
