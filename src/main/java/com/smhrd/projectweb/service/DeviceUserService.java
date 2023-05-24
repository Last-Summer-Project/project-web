package com.smhrd.projectweb.service;

import com.smhrd.projectweb.mapper.DeviceMapper;
import com.smhrd.projectweb.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class DeviceUserService {

    private final DeviceMapper deviceMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public String authenticate(String loginId, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
        return jwtProvider.createToken(loginId, deviceMapper.selectByLoginId(loginId).getId());
    }

    public Long getTokenDeviceId(HttpServletRequest req) {
        return jwtProvider.getDeviceId(jwtProvider.resolveToken(req));
    }

    public String getTokenLoginId(HttpServletRequest req) {
        return jwtProvider.getLoginId(jwtProvider.resolveToken(req));
    }

    public String refresh(String loginId) {
       return jwtProvider.createToken(loginId, deviceMapper.selectByLoginId(loginId).getId());
    }
}