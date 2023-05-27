package com.smhrd.projectweb.payload.request.api.v1.v1.log;

import com.smhrd.projectweb.entity.DeviceLog;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogWriteRequest {
    private Long deviceId;
    private Double temperature;
    private Double relativeHumidity;
    private Double soilHumidity;
    private String imageBase64;

    public DeviceLog toDeviceLog() {
        DeviceLog dl = new DeviceLog();
        dl.setDeviceId(deviceId);
        dl.setTemperature(temperature);
        dl.setRelativeHumidity(relativeHumidity);
        dl.setSoilHumidity(soilHumidity);
        return dl;
    }
}
