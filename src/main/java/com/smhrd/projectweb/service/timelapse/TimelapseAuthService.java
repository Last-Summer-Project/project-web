package com.smhrd.projectweb.service.timelapse;

import com.smhrd.projectweb.payload.request.api.v1.v1.timelapse.TimelapseRequest;
import com.smhrd.projectweb.payload.response.api.v1.v1.timelapse.TimelapseResponse;
import com.smhrd.projectweb.service.device.DeviceUserService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelapseAuthService {
    private final TimelapseService timelapseService;
    private final DeviceUserService deviceUserService;

    private Long getDeviceId(HttpServletRequest req) {
        return deviceUserService.getTokenDeviceId(req);
    }

    public ResultWrapper<TimelapseResponse> getLatest(HttpServletRequest request) {
        return timelapseService.getLatest(getDeviceId(request));
    }

    public ResultWrapper<TimelapseResponse> postRequest(HttpServletRequest request, TimelapseRequest timelapseRequest) {
        return timelapseService.postRequest(getDeviceId(request), timelapseRequest);
    }

    public ResultWrapper<List<TimelapseResponse>> getAll(HttpServletRequest request) {
        return timelapseService.getAll(getDeviceId(request));
    }
}
