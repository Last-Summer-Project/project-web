package com.smhrd.projectweb.payload.response.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smhrd.projectweb.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class DeviceResponse {
    private Long id;
    /**
     *
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private OffsetDateTime dateCreated;

    /**
     *
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private OffsetDateTime lastEdited;

    public static DeviceResponse fromDevice(Device device) {
        return new DeviceResponse(device.getId(), device.getDateCreated(), device.getLastEdited());
    }
}
