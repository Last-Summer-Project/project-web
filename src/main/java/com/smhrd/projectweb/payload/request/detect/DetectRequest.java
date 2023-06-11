package com.smhrd.projectweb.payload.request.detect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DetectRequest {
    private String imageBase64;
}
