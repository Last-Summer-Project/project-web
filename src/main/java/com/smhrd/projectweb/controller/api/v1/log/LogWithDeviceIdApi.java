package com.smhrd.projectweb.controller.api.v1.log;

import com.smhrd.projectweb.payload.request.api.v1.log.LogWriteRequest;
import com.smhrd.projectweb.payload.response.api.v1.log.LogResponse;
import com.smhrd.projectweb.service.log.DeviceLogService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/log/{deviceId}")
@RestController
@RequiredArgsConstructor
@Slf4j
public class LogWithDeviceIdApi {
    private final DeviceLogService deviceLogService;

    @GetMapping("/latest")
    public ResultWrapper<LogResponse> getLatestLog(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getLatestByDeviceId(deviceId);
    }

    @GetMapping("/latest-detected")
    public ResultWrapper<LogResponse> getLatestDetectedLog(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getLatestDetectedByDeviceId(deviceId);
    }


    @GetMapping("/detected-per-day")
    public ResultWrapper<List<LogResponse>> getDetectedPerDay(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getDetectedByDeviceIdPerDay(deviceId);
    }


    @GetMapping("/recent")
    public ResultWrapper<List<LogResponse>> getRecent(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getRecentByDeviceId(deviceId);
    }

    @PostMapping("/write")
    public ResultWrapper<LogResponse> logWrite(@PathVariable(name = "deviceId") Long deviceId, @RequestBody LogWriteRequest requestBody) {
        return deviceLogService.writeLog(deviceId, requestBody);
    }
}
