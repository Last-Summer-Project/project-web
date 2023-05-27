package com.smhrd.projectweb.controller.api.v1.device;

import com.smhrd.projectweb.entity.request.api.v1.auth.AuthRequest;
import com.smhrd.projectweb.entity.request.api.v1.auth.RefreshRequest;
import com.smhrd.projectweb.entity.response.api.v1.device.DeviceAuthResponse;
import com.smhrd.projectweb.service.device.DeviceAuthService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthApi {
    private final DeviceAuthService deviceAuthService;

    @PostMapping("/login")
    public ResultWrapper<DeviceAuthResponse> login(@RequestBody AuthRequest authRequest) {
        return deviceAuthService.login(authRequest);
    }

    @PostMapping("/signup")
    public ResultWrapper<DeviceAuthResponse> signup(@RequestBody AuthRequest authRequest) {
        return deviceAuthService.signup(authRequest);
    }

    @PostMapping("/refresh")
    public ResultWrapper<DeviceAuthResponse> refresh(@RequestBody RefreshRequest request) {
        return deviceAuthService.refreshToken(request);
    }

    @GetMapping("/verify")
    public ResultWrapper<Void> verify(HttpServletRequest request) {
        return deviceAuthService.verifyToken(request);
    }
}
