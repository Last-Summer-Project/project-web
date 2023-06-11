package com.smhrd.projectweb.payload.response.detect;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smhrd.projectweb.entity.Detect;
import com.smhrd.projectweb.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DetectResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Status status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;

    public static DetectResponse fromDetect(Detect detect) {
        return new DetectResponse(detect.getStatus(), detect.getResult());
    }
}
