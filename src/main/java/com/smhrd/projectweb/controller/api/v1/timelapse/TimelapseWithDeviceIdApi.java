package com.smhrd.projectweb.controller.api.v1.timelapse;

import com.smhrd.projectweb.payload.request.api.v1.timelapse.TimelapseRequest;
import com.smhrd.projectweb.payload.response.api.v1.timelapse.TimelapseResponse;
import com.smhrd.projectweb.service.timelapse.TimelapseService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/timelapse/{deviceId}")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TimelapseWithDeviceIdApi {
    private final TimelapseService timelapseService;

    @GetMapping("/latest")
    public ResultWrapper<TimelapseResponse> getLatest(@PathVariable("deviceId") Long deviceId) {
        return timelapseService.getLatest(deviceId);
    }

    @PostMapping("/request")
    public ResultWrapper<TimelapseResponse> postRequest(@PathVariable("deviceId") Long deviceId, @RequestBody TimelapseRequest request) {
        return timelapseService.postRequest(deviceId, request);
    }

    @GetMapping("/all")
    public ResultWrapper<List<TimelapseResponse>> getAll(@PathVariable("deviceId") Long deviceId) {
        return timelapseService.getAll(deviceId);
    }
}
