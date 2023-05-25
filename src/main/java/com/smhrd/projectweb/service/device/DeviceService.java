package com.smhrd.projectweb.service.device;

import com.smhrd.projectweb.entity.response.api.v1.device.DeviceResponse;
import com.smhrd.projectweb.entity.sql.Device;
import com.smhrd.projectweb.mapper.DeviceMapper;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceMapper deviceMapper;

    private final DeviceUserService deviceUserService;

    private Long getDeviceId(HttpServletRequest req) {
        return deviceUserService.getTokenDeviceId(req);
    }

    public ResultWrapper<DeviceResponse> get(HttpServletRequest req) {
        return this.get(getDeviceId(req));
    }

    public ResultWrapper<DeviceResponse> update(HttpServletRequest req, Device requestDevice) {
        return this.update(getDeviceId(req), requestDevice);
    }

    public ResultWrapper<DeviceResponse> get(Long id) {
        Device device = deviceMapper.selectByPrimaryKey(id);
        if (device == null) {
            return ResultWrapper.error(404, "No device found");
        }
        return ResultWrapper.ok(DeviceResponse.fromDevice(device));
    }

    public ResultWrapper<DeviceResponse> update(Long id, Device requestDevice) {
        return ResultWrapper.fail("Not implemented", DeviceResponse.fromDevice(requestDevice));
    }
}
