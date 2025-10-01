package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.entity.User;
import com.nimbusds.jwt.SignedJWT;

public interface TokenService {

    SignedJWT verifyToken(String token);

    String generateToken(User user);
}
