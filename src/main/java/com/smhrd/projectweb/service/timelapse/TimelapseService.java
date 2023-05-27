package com.smhrd.projectweb.service.timelapse;

import com.smhrd.projectweb.payload.request.api.v1.v1.timelapse.TimelapseRequest;
import com.smhrd.projectweb.payload.response.api.v1.v1.timelapse.TimelapseResponse;
import com.smhrd.projectweb.mapper.TimelapseMapper;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelapseService {
    private final TimelapseMapper timelapseMapper;

    public ResultWrapper<TimelapseResponse> getLatest(Long deviceId) {
        return ResultWrapper.error("Not implemented");
    }

    public ResultWrapper<TimelapseResponse> postRequest(Long deviceId, TimelapseRequest request) {
        return ResultWrapper.error("Not implemented");
    }

    public ResultWrapper<List<TimelapseResponse>> getAll(Long deviceId) {
        return ResultWrapper.error("Not implemented");
    }
}
