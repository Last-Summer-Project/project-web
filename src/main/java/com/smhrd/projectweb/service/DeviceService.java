package com.smhrd.projectweb.service;

import com.smhrd.projectweb.entity.response.api.v1.device.DeviceResponse;
import com.smhrd.projectweb.entity.sql.Device;
import com.smhrd.projectweb.mapper.DeviceMapper;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceMapper deviceMapper;

    public ResultWrapper<DeviceResponse> get(Long id) {
        Device device = deviceMapper.selectByPrimaryKey(id);
        if (device == null) {
            return ResultWrapper.error("No device found");
        }
        return ResultWrapper.ok(DeviceResponse.fromDevice(device));
    }
}
