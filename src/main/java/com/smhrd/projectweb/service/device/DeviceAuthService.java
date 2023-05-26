package com.smhrd.projectweb.service.device;

import com.smhrd.projectweb.entity.request.api.v1.AuthRequest;
import com.smhrd.projectweb.entity.sql.Device;
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


    public ResultWrapper<String> signup(AuthRequest authRequest) {
        try {
            if (isSignupAllowed == null || !isSignupAllowed) return ResultWrapper.error("Signup is not allowed");

            Device device = authRequest.toDevice();
            String token = deviceUserService.signup(device);
            if (token == null || token.isEmpty()) return ResultWrapper.fail(403, "Failed to sign up");

            return ResultWrapper.ok("Successfully signed up", token);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultWrapper.error("Failed to sign up");
        }
    }

    public ResultWrapper<String> login(AuthRequest authRequest) {
        try {
            String token = deviceUserService.login(authRequest.getLoginId(), authRequest.getPassword());
            if (token == null || token.isEmpty()) return ResultWrapper.fail(401, LOGIN_FAILED);

            return ResultWrapper.ok("Successfully logged in", token);
        } catch (BadCredentialsException e) {
            return ResultWrapper.fail(401, LOGIN_FAILED);
        } catch (Exception e) {
            return ResultWrapper.error(LOGIN_FAILED);
        }
    }

    public ResultWrapper<String> refreshToken(HttpServletRequest request) {
        try {
            String token = deviceUserService.refresh(deviceUserService.getTokenLoginId(request));
            if (token == null || token.isEmpty()) return ResultWrapper.fail(403, "Failed to refresh token");

            return ResultWrapper.ok("Successfully refreshed token", token);
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
