package com.smhrd.projectweb.payload.response.api.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class IndexResponse {
    private String message;
}