package com.smhrd.projectweb.payload.response.api.v1.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smhrd.projectweb.entity.Device;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime dateCreated;

    /**
     *
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime lastEdited;

    public static DeviceResponse fromDevice(Device device) {
        return new DeviceResponse(device.getId(), device.getDateCreated(), device.getLastEdited());
    }
}
