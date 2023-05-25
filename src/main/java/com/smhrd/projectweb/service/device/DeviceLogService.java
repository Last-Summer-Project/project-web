package com.smhrd.projectweb.service.device;

import com.smhrd.projectweb.entity.request.api.v1.log.LogWriteRequest;
import com.smhrd.projectweb.entity.response.api.v1.log.LogResponse;
import com.smhrd.projectweb.entity.sql.DeviceLog;
import com.smhrd.projectweb.mapper.DetectMapper;
import com.smhrd.projectweb.mapper.DeviceLogMapper;
import com.smhrd.projectweb.mapper.ImageMapper;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogMapper deviceLogMapper;
    private final ImageMapper imageMapper;
    private final DetectMapper detectMapper;

    public ResultWrapper<LogResponse> getLatestByDeviceId(Long id) {
        DeviceLog dl = deviceLogMapper.selectLatestByDeviceId(id);
        if (dl == null) {
            return ResultWrapper.error("No log found");
        }
        return ResultWrapper.ok(LogResponse.fromDeviceLog(dl));
    }

    public ResultWrapper<List<LogResponse>> getAllByDeviceId(Long id) {
        List<DeviceLog> dll = deviceLogMapper.selectByDeviceId(id);
        if (dll == null || dll.isEmpty()) {
            return ResultWrapper.error("No log found");
        }
        // List<DeviceLog>를 List<LogResponse>>로 변환
        List<LogResponse> llr = dll.stream().map(LogResponse::fromDeviceLog).collect(Collectors.toList());

        return ResultWrapper.ok(llr);
    }

    public ResultWrapper<Void> writeLog(Long deviceId, LogWriteRequest request) {
        return ResultWrapper.fail("Not Implemented");
    }
}
