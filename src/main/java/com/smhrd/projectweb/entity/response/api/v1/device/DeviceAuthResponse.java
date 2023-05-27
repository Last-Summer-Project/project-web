package com.smhrd.projectweb.entity.response.api.v1.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceAuthResponse {
    private String access;
    private String refresh;

    @JsonIgnore
    public boolean isInvalid() {
        return access == null || access.isEmpty() || refresh == null || refresh.isEmpty();
    }
}
