package com.smhrd.projectweb.entity.request.api.v1;

import com.smhrd.projectweb.entity.sql.Device;
import lombok.Data;

@Data
public class AuthRequest {
    private String loginId;
    private String password;

    public Device toDevice() {
        return new Device(0L, loginId, password, null, null);
    }
}
