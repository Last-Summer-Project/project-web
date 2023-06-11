package com.smhrd.projectweb.payload.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RefreshRequest implements Serializable {
    private String refresh;
}
