package com.smhrd.projectweb.controller.api.v1.device;

import com.smhrd.projectweb.entity.response.api.v1.device.DeviceResponse;
import com.smhrd.projectweb.entity.sql.Device;
import com.smhrd.projectweb.service.device.DeviceService;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceApi {
    private final DeviceService deviceService;

    @GetMapping("/get/{id}")
    public ResultWrapper<DeviceResponse> get(@PathVariable(value = "id") Long id) {
        return deviceService.get(id);
    }

    @PostMapping("/update/{id}")
    public ResultWrapper<DeviceResponse> update(@PathVariable(value = "id") Long id, @RequestBody Device requestDevice) {
        return deviceService.update(id, requestDevice);
    }
}
