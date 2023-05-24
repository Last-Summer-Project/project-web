package com.smhrd.projectweb.entity.request.api.v1.log;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class LogWriteRequest {
    private Long deviceId;
    private Double temperature;
    private Double relativeHumidity;
    private Double soilHumidity;
    private String imageBase64;
}
