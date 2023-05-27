package com.smhrd.projectweb.service.device;

import com.smhrd.projectweb.payload.request.api.v1.auth.AuthRequest;
import com.smhrd.projectweb.payload.request.api.v1.auth.RefreshRequest;
import com.smhrd.projectweb.payload.response.api.v1.device.DeviceAuthResponse;
import com.smhrd.projectweb.entity.Device;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceAuthService {

    private final DeviceUserService deviceUserService;

    private static final String LOGIN_FAILED = "Failed to login";

    @Value("${service.debug.allowSignup}")
    private Boolean isSignupAllowed;


    public ResultWrapper<DeviceAuthResponse> signup(AuthRequest authRequest) {
        try {
            if (isSignupAllowed == null || !isSignupAllowed) return ResultWrapper.error("Signup is not allowed");

            Device device = authRequest.toDevice();
            DeviceAuthResponse response = deviceUserService.signup(device);
            if (response.isInvalid()) return ResultWrapper.fail(403, "Failed to sign up");

            return ResultWrapper.ok("Successfully signed up", response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultWrapper.error("Failed to sign up");
        }
    }

    public ResultWrapper<DeviceAuthResponse> login(AuthRequest authRequest) {
        try {
            DeviceAuthResponse response = deviceUserService.login(authRequest.getLoginId(), authRequest.getPassword());
            if (response.isInvalid()) return ResultWrapper.fail(401, LOGIN_FAILED);

            return ResultWrapper.ok("Successfully logged in", response);
        } catch (BadCredentialsException e) {
            return ResultWrapper.fail(401, LOGIN_FAILED);
        } catch (Exception e) {
            return ResultWrapper.error(LOGIN_FAILED);
        }
    }

    public ResultWrapper<DeviceAuthResponse> refreshToken(RefreshRequest request) {
        try {
            DeviceAuthResponse response = deviceUserService.refresh(deviceUserService.getTokenLoginId(request.getRefresh()));
            if (response.isInvalid()) return ResultWrapper.fail(403, "Failed to refresh token");

            return ResultWrapper.ok("Successfully refreshed token", response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultWrapper.error("Failed to refresh token");
        }
    }

    public ResultWrapper<Void> verifyToken(HttpServletRequest request) {
        try {
            deviceUserService.getTokenDeviceId(request);
            deviceUserService.getTokenLoginId(request);
            return ResultWrapper.ok();
        } catch (Exception e) {
            return ResultWrapper.fail(401, "Unauthorized");
        }
    }
}
