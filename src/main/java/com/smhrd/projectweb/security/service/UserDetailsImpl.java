package com.smhrd.projectweb.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smhrd.projectweb.entity.Device;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class UserDetailsImpl implements UserDetails {
    private Long id;

    /** Actually, it's {@see Device#loginId}. */
    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    public static UserDetailsImpl build(Device device) {
        // We don't have real role...
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("device")
        );

        return new UserDetailsImpl(
                device.getId(),
                device.getLoginId(),
                device.getPassword(),
                authorities
        );
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
