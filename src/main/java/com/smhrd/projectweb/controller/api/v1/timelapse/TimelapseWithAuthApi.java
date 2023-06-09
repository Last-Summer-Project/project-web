package com.smhrd.projectweb.controller.api.v1.timelapse;

import com.smhrd.projectweb.payload.request.api.v1.timelapse.TimelapseRequest;
import com.smhrd.projectweb.payload.response.api.v1.timelapse.TimelapseResponse;
import com.smhrd.projectweb.service.timelapse.TimelapseAuthService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/timelapse")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TimelapseWithAuthApi {
    private final TimelapseAuthService timelapseAuthService;

    @GetMapping("/latest")
    public ResultWrapper<TimelapseResponse> getLatest(HttpServletRequest request) {
        return timelapseAuthService.getLatest(request);
    }

    @PostMapping("/request")
    public ResultWrapper<TimelapseResponse> postRequest(HttpServletRequest request, @RequestBody TimelapseRequest timelapseRequest) {
        return timelapseAuthService.postRequest(request, timelapseRequest);
    }

    @GetMapping("/all")
    public ResultWrapper<List<TimelapseResponse>> getAll(HttpServletRequest request) {
        return timelapseAuthService.getAll(request);
    }
}
