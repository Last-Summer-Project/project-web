package com.smhrd.projectweb.payload.response.api.v1.timelapse;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smhrd.projectweb.entity.Status;
import com.smhrd.projectweb.entity.Timelapse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Slf4j
public class TimelapseResponse {

    private Long id;

    private Long deviceId;

    private Status status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime logStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime logEndDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime lastUpdated;


    public static TimelapseResponse fromTimelapse(Timelapse timelapse) {
        return new TimelapseResponse(
                timelapse.getId(),
                timelapse.getDeviceId(),
                timelapse.getStatus(),
                timelapse.getResult(),
                timelapse.getStartDate(),
                timelapse.getEndDate(),
                timelapse.getLastEdited()
        );
    }
}
