package com.smhrd.projectweb.service.timelapse;

import com.smhrd.projectweb.entity.Timelapse;
import com.smhrd.projectweb.mapper.TimelapseMapper;
import com.smhrd.projectweb.payload.request.api.v1.timelapse.TimelapseRequest;
import com.smhrd.projectweb.payload.response.api.v1.timelapse.TimelapseResponse;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimelapseService {
    private static final String TIMELAPSE_NOT_FOUND = "Timelapse not found";
    private final TimelapseMapper timelapseMapper;

    public ResultWrapper<TimelapseResponse> getLatest(Long deviceId) {
        Timelapse timelapse = timelapseMapper.selectLatestByDeviceId(deviceId);
        if (timelapse == null || timelapse.getId() == 0) {
            return ResultWrapper.fail(404, TIMELAPSE_NOT_FOUND);
        }

        return ResultWrapper.ok(TimelapseResponse.fromTimelapse(timelapse));
    }

    public ResultWrapper<TimelapseResponse> postRequest(Long deviceId, TimelapseRequest request) {
        return ResultWrapper.error("Not implemented");
    }

    public ResultWrapper<List<TimelapseResponse>> getAll(Long deviceId) {
        List<Timelapse> tll = timelapseMapper.selectByDeviceId(deviceId);
        if (tll == null || tll.isEmpty()) {
            return ResultWrapper.fail(404, TIMELAPSE_NOT_FOUND);
        }
        // List<Timelapse>를 List<TimelapseResponse>>로 변환
        List<TimelapseResponse> tlr = tll.stream().map(TimelapseResponse::fromTimelapse).collect(Collectors.toList());

        return ResultWrapper.ok(tlr);
    }
}
