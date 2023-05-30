package com.smhrd.projectweb.payload.response.api.v1.timelapse;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.smhrd.projectweb.entity.Status;
import com.smhrd.projectweb.entity.Timelapse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimelapseResponse {

    private Long id;

    private Long deviceId;

    private Status status;

    private String result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime logStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private LocalDateTime logEndDate;

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
