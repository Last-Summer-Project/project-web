package com.smhrd.projectweb.payload.response.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smhrd.projectweb.payload.response.detect.DetectResponse;
import com.smhrd.projectweb.entity.Detect;
import com.smhrd.projectweb.entity.DeviceLog;
import com.smhrd.projectweb.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Slf4j
public class LogResponse {
    private Long deviceId;

    private Double humidity;

    private Double temperature;

    private String imageUrl;

    private DetectResponse detection;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private OffsetDateTime timestamp;

    public static LogResponse fromDeviceLog(DeviceLog deviceLog) {
        Detect d = deviceLog.getDetect();
        Image i = deviceLog.getImage();
        return new LogResponse(
                deviceLog.getDeviceId(),
                deviceLog.getRelativeHumidity(),
                deviceLog.getTemperature(),
                i.getUrl(),
                DetectResponse.fromDetect(d),
                deviceLog.getDateCreated());
    }
}
