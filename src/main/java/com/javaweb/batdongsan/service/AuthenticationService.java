package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.authentication.AuthenticationRequest;
import com.javaweb.batdongsan.model.request.authentication.IntrospectRequest;
import com.javaweb.batdongsan.model.request.authentication.LogOutRequest;
import com.javaweb.batdongsan.model.request.authentication.RefreshRequest;
import com.javaweb.batdongsan.model.response.authentication.AuthenticationResponse;
import com.javaweb.batdongsan.model.response.authentication.IntrospectResponse;
import com.javaweb.batdongsan.model.response.authentication.RefreshResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse validToken(IntrospectRequest request);

    Void logout(LogOutRequest request);

    RefreshResponse refreshToken(RefreshRequest request);
}
