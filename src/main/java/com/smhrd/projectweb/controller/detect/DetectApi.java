package com.smhrd.projectweb.controller.detect;

import com.smhrd.projectweb.payload.request.detect.DetectRequest;
import com.smhrd.projectweb.payload.response.detect.DetectResponse;
import com.smhrd.projectweb.service.detect.DetectService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detect")
@RequiredArgsConstructor
@Slf4j
public class DetectApi {
    private final DetectService detectService;

    @PostMapping("/request")
    public ResultWrapper<DetectResponse> request(@RequestBody DetectRequest detectRequest) {
        return detectService.request(detectRequest);
    }
}
