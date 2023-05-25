package com.smhrd.projectweb.controller.api.v1;

import com.smhrd.projectweb.entity.request.api.v1.log.LogWriteRequest;
import com.smhrd.projectweb.entity.response.api.v1.log.LogResponse;
import com.smhrd.projectweb.service.device.DeviceLogService;
import com.smhrd.projectweb.service.device.DeviceUserService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/v1/log")
@RestController
@RequiredArgsConstructor
@Slf4j
public class LogApi {

    private final DeviceLogService deviceLogService;
    private final DeviceUserService deviceUserService;

    @GetMapping("/{deviceId}/latest")
    public ResultWrapper<LogResponse> getLatestLog(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getLatestByDeviceId(deviceId);
    }

    @GetMapping("/{deviceId}/latest-detected")
    public ResultWrapper<LogResponse> getLatestDetectedLog(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getLatestDetectedByDeviceId(deviceId);
    }

    @GetMapping("/{deviceId}/recent")
    public ResultWrapper<List<LogResponse>> getRecent(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogService.getRecentByDeviceId(deviceId);
    }

    @PostMapping("/write")
    public ResultWrapper<Void> logWrite(HttpServletRequest req, @RequestBody LogWriteRequest requestBody) {
        return deviceLogService.writeLog(deviceUserService.getTokenDeviceId(req), requestBody);
    }
}
