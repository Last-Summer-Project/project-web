package com.smhrd.projectweb.service.log;

import com.smhrd.projectweb.payload.request.log.LogWriteRequest;
import com.smhrd.projectweb.payload.response.log.LogResponse;
import com.smhrd.projectweb.service.device.DeviceUserService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceLogAuthService {
    private final DeviceLogService deviceLogService;
    private final DeviceUserService deviceUserService;

    private Long getDeviceId(HttpServletRequest req) {
        return deviceUserService.getTokenDeviceId(req);
    }

    public ResultWrapper<LogResponse> getLatestByDeviceId(HttpServletRequest req) {
        return deviceLogService.getLatestByDeviceId(getDeviceId(req));
    }

    public ResultWrapper<LogResponse> getLatestDetectedByDeviceId(HttpServletRequest req) {
        return deviceLogService.getLatestDetectedByDeviceId(getDeviceId(req));
    }

    public ResultWrapper<List<LogResponse>> getDetectedByDeviceIdPerDay(HttpServletRequest req) {
        return deviceLogService.getDetectedByDeviceIdPerDay(getDeviceId(req));
    }

    public ResultWrapper<List<LogResponse>> getRecentByDeviceId(HttpServletRequest req) {
        return deviceLogService.getRecentByDeviceId(getDeviceId(req));
    }

    public ResultWrapper<LogResponse> writeLog(HttpServletRequest req, LogWriteRequest request) {
        return deviceLogService.writeLog(getDeviceId(req), request);
    }
}
