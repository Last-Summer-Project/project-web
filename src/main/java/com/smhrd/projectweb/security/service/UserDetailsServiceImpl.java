package com.smhrd.projectweb.security.service;

import com.smhrd.projectweb.entity.Device;
import com.smhrd.projectweb.mapper.DeviceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DeviceMapper deviceMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Device device = deviceMapper.selectByLoginId(username);

        if (device == null) {
            throw new UsernameNotFoundException(String.format("Device login id '%s' not found", username));
        }

        return UserDetailsImpl.build(device);
    }
}
