package com.smhrd.projectweb.service;

import com.smhrd.projectweb.entity.request.api.v1.AuthRequest;
import com.smhrd.projectweb.entity.sql.Device;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class DeviceAuthService {
    private final DeviceUserService deviceUserService;

    @Value("${service.debug.allowSignup}")
    private Boolean isSignupAllowed;

    public ResultWrapper<String> signup(AuthRequest authRequest) {
        if (isSignupAllowed == null || !isSignupAllowed) return ResultWrapper.error("Signup is not allowed");

        Device device = authRequest.toDevice();
        String token = deviceUserService.signup(device);
        if (token == null || token.isEmpty()) return ResultWrapper.fail("Failed to sign up");

        return ResultWrapper.ok("Sign up complete", token);
    }

    public ResultWrapper<String> login(AuthRequest authRequest) {
        String token = deviceUserService.login(authRequest.getLoginId(), authRequest.getPassword());
        if (token == null || token.isEmpty()) return ResultWrapper.fail("Failed to login");

        return ResultWrapper.ok("Successfully logged in", token);
    }

    public ResultWrapper<String> refreshToken(HttpServletRequest request) {
        String token = deviceUserService.refresh(deviceUserService.getTokenLoginId(request));
        if (token == null || token.isEmpty()) return ResultWrapper.fail("Failed to refresh token");

        return ResultWrapper.ok("Successfully refreshed token", token);
    }
}
