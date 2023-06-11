package com.smhrd.projectweb.service.log;

import com.smhrd.projectweb.entity.Detect;
import com.smhrd.projectweb.entity.DeviceLog;
import com.smhrd.projectweb.mapper.DetectMapper;
import com.smhrd.projectweb.mapper.DeviceLogMapper;
import com.smhrd.projectweb.payload.request.log.LogWriteRequest;
import com.smhrd.projectweb.payload.response.log.LogResponse;
import com.smhrd.projectweb.service.ImageService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceLogService {
    private static final String NO_LOG_FOUND = "No log found";
    private final DeviceLogMapper deviceLogMapper;
    private final DetectMapper detectMapper;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public ResultWrapper<LogResponse> getLatestByDeviceId(Long id) {
        DeviceLog dl = deviceLogMapper.selectLatestByDeviceId(id);
        if (dl == null) {
            return ResultWrapper.fail(404, NO_LOG_FOUND);
        }
        return ResultWrapper.ok(LogResponse.fromDeviceLog(dl));
    }

    @Transactional(readOnly = true)
    public ResultWrapper<LogResponse> getLatestDetectedByDeviceId(Long id) {
        DeviceLog dl = deviceLogMapper.selectLatestDetectedByDeviceId(id);
        if (dl == null) {
            return ResultWrapper.fail(404, NO_LOG_FOUND);
        }
        return ResultWrapper.ok(LogResponse.fromDeviceLog(dl));
    }

    @Transactional(readOnly = true)
    public ResultWrapper<List<LogResponse>> getDetectedByDeviceIdPerDay(Long id) {
        List<DeviceLog> dll = deviceLogMapper.selectDetectedByDeviceIdPerDay(id);
        if (dll == null || dll.isEmpty()) {
            return ResultWrapper.fail(404, NO_LOG_FOUND);
        }
        // List<DeviceLog>를 List<LogResponse>>로 변환
        List<LogResponse> llr = dll.stream().map(LogResponse::fromDeviceLog).collect(Collectors.toList());

        return ResultWrapper.ok(llr);
    }

    @Transactional(readOnly = true)
    public ResultWrapper<List<LogResponse>> getRecentByDeviceId(Long id) {
        List<DeviceLog> dll = deviceLogMapper.selectByDeviceId(id);
        if (dll == null || dll.isEmpty()) {
            return ResultWrapper.fail(404, NO_LOG_FOUND);
        }
        // List<DeviceLog>를 List<LogResponse>>로 변환
        List<LogResponse> llr = dll.stream().map(LogResponse::fromDeviceLog).collect(Collectors.toList());

        return ResultWrapper.ok(llr);
    }

    @Transactional
    public ResultWrapper<LogResponse> writeLog(Long deviceId, LogWriteRequest request) {
        if (!deviceId.equals(request.getDeviceId())) return ResultWrapper.fail("Invalid request");

        String imageName = UUID.randomUUID().toString();
        Long imageId = imageService.uploadImageFromBase64(imageName, request.getImageBase64());
        if (imageId == null) return ResultWrapper.error("Failed to upload image");

        DeviceLog deviceLog = request.toDeviceLog();
        deviceLog.setImageId(imageId);
        Long logInserted = deviceLogMapper.insert(deviceLog);
        if (logInserted != 1) return ResultWrapper.error("Failed to log data.");
        Long logId = deviceLog.getId();
        if (logId == null || logId == 0) return ResultWrapper.error("Failed to log data.");

        Long detectInserted = detectMapper.insert(new Detect(logId));
        if (detectInserted != 1) return ResultWrapper.error("Failed to log detect data.");

        deviceLog = deviceLogMapper.selectByPrimaryKey(logId);
        if (deviceLog == null) return ResultWrapper.error("Failed to load saved log data");
        return ResultWrapper.ok(LogResponse.fromDeviceLog(deviceLog));
    }
}
