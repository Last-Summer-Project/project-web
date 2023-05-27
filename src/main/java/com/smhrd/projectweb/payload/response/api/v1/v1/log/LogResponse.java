package com.smhrd.projectweb.payload.response.api.v1.v1.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smhrd.projectweb.payload.response.api.v1.v1.detect.DetectResponse;
import com.smhrd.projectweb.entity.Detect;
import com.smhrd.projectweb.entity.DeviceLog;
import com.smhrd.projectweb.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Slf4j
public class LogResponse {
    private Long deviceId;

    private Double humidity;

    private Double temperature;

    private Double soilHumidity;

    private String imageUrl;

    private DetectResponse detection;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime timestamp;

    public static LogResponse fromDeviceLog(DeviceLog deviceLog) {
        Detect d = deviceLog.getDetect();
        Image i = deviceLog.getImage();
        return new LogResponse(
                deviceLog.getDeviceId(),
                deviceLog.getRelativeHumidity(),
                deviceLog.getTemperature(),
                deviceLog.getSoilHumidity(),
                i.getUrl(),
                DetectResponse.fromDetect(d),
                deviceLog.getDateCreated());
    }
}
