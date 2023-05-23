package com.smhrd.projectweb.controller.api.v1.log;

import com.smhrd.projectweb.entity.sql.DeviceLog;
import com.smhrd.projectweb.mapper.DeviceLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/log")
@RestController
@RequiredArgsConstructor
public class LogApi {

    private final DeviceLogMapper deviceLogMapper;

    @GetMapping("/{deviceId}/latest")
    public DeviceLog getLatestLog(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogMapper.selectByDeviceId(deviceId).get(0);
    }

    @GetMapping("/{deviceId}/all")
    public List<DeviceLog> getAllLog(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceLogMapper.selectByDeviceId(deviceId);
    }
}
