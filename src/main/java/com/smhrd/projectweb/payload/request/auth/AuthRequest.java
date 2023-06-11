package com.smhrd.projectweb.payload.request.auth;

import com.smhrd.projectweb.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthRequest implements Serializable {
    private String loginId;
    private String password;

    public Device toDevice() {
        return new Device(0L, loginId, password, null, null);
    }
}
