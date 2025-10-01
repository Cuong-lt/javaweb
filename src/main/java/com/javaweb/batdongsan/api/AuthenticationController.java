package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.authentication.AuthenticationRequest;
import com.javaweb.batdongsan.model.request.authentication.IntrospectRequest;
import com.javaweb.batdongsan.model.request.authentication.LogOutRequest;
import com.javaweb.batdongsan.model.request.authentication.RefreshRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.authentication.AuthenticationResponse;
import com.javaweb.batdongsan.model.response.authentication.IntrospectResponse;
import com.javaweb.batdongsan.model.response.authentication.RefreshResponse;
import com.javaweb.batdongsan.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/log-in")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        response.setResult(authenticationService.authenticate(request));
        return response;
    }
    @PostMapping("/log-out")
    public ApiResponse<Void> authenticate(@RequestBody LogOutRequest request){
        ApiResponse<Void> response = new ApiResponse<>();
        response.setResult(authenticationService.logout(request));
        response.setMessage("log out successfully");
        return response;
    }

    @PostMapping("/valid-token")
    public ApiResponse<IntrospectResponse> validToken(@RequestBody IntrospectRequest request){
        ApiResponse<IntrospectResponse> response = new ApiResponse<>();
        response.setResult(authenticationService.validToken(request));
        return response;
    }

    @PostMapping("/refresh")
    ApiResponse<RefreshResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<RefreshResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }

}
