package com.smhrd.projectweb.entity.response.api.v1.device;

import com.smhrd.projectweb.entity.sql.Device;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeviceResponse {
    private Long id;
    /**
     *
     */
    private LocalDateTime dateCreated;

    /**
     *
     */
    private LocalDateTime lastEdited;

    public static DeviceResponse fromDevice(Device device) {
        return new DeviceResponse(device.getId(), device.getDateCreated(), device.getLastEdited());
    }
}
