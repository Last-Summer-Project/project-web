package com.smhrd.projectweb.controller.api.v1.log;

import com.smhrd.projectweb.payload.request.api.v1.log.LogWriteRequest;
import com.smhrd.projectweb.payload.response.api.v1.log.LogResponse;
import com.smhrd.projectweb.service.log.DeviceLogAuthService;
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
public class LogWithAuthApi {
    private final DeviceLogAuthService deviceAuthLogService;

    @GetMapping("/latest")
    public ResultWrapper<LogResponse> getLatestLog(HttpServletRequest request) {
        return deviceAuthLogService.getLatestByDeviceId(request);
    }

    @GetMapping("/latest-detected")
    public ResultWrapper<LogResponse> getLatestDetectedLog(HttpServletRequest request) {
        return deviceAuthLogService.getLatestDetectedByDeviceId(request);
    }

    @GetMapping("/detected-per-day")
    public ResultWrapper<List<LogResponse>> getDetectedPerDay(HttpServletRequest request) {
        return deviceAuthLogService.getDetectedByDeviceIdPerDay(request);
    }

    @GetMapping("/recent")
    public ResultWrapper<List<LogResponse>> getRecent(HttpServletRequest request) {
        return deviceAuthLogService.getRecentByDeviceId(request);
    }

    @PostMapping("/write")
    public ResultWrapper<LogResponse> logWrite(HttpServletRequest request, @RequestBody LogWriteRequest requestBody) {
        return deviceAuthLogService.writeLog(request, requestBody);
    }
}
