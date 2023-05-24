package com.smhrd.projectweb.entity.response.api.v1.detect;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smhrd.projectweb.entity.sql.Detect;
import com.smhrd.projectweb.entity.sql.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetectResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Status detectStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detectResult;

    public static DetectResponse fromDetect(Detect detect) {
        return new DetectResponse(detect.getStatus(), detect.getResult());
    }
}
