package com.smhrd.projectweb.service.device;

import com.smhrd.projectweb.payload.response.device.DeviceAuthResponse;
import com.smhrd.projectweb.entity.Device;
import com.smhrd.projectweb.mapper.DeviceMapper;
import com.smhrd.projectweb.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class DeviceUserService {

    private final DeviceMapper deviceMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Transactional(readOnly = true)
    public DeviceAuthResponse login(String loginId, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
        return jwtProvider.createToken(loginId, deviceMapper.selectByLoginId(loginId).getId());
    }

    @Transactional
    public DeviceAuthResponse signup(Device device) {
        Device d = deviceMapper.selectByLoginId(device.getLoginId());
        if (d == null) {
            device.setPassword(passwordEncoder.encode(device.getPassword()));
            Long id = deviceMapper.insert(device);
            return jwtProvider.createToken(device.getLoginId(), id);
        }
        return null;
    }

    public Long getTokenDeviceId(HttpServletRequest req) {
        return getTokenDeviceId(jwtProvider.resolveToken(req));
    }

    public String getTokenLoginId(HttpServletRequest req) {
        return getTokenLoginId(jwtProvider.resolveToken(req));
    }

    public Long getTokenDeviceId(String token) {
        return jwtProvider.getDeviceId(token);
    }

    public String getTokenLoginId(String token) {
        return jwtProvider.getLoginId(token);
    }

    @Transactional(readOnly = true)
    public DeviceAuthResponse refresh(String loginId) {
        return jwtProvider.createToken(loginId, deviceMapper.selectByLoginId(loginId).getId());
    }
}
