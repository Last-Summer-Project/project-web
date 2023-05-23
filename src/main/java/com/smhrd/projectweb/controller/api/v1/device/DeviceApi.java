package com.smhrd.projectweb.controller.api.v1.device;

import com.smhrd.projectweb.entity.sql.Device;
import com.smhrd.projectweb.mapper.DeviceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceApi {
    private final DeviceMapper deviceMapper;

    @GetMapping("/get/{id}")
    public Device get(@PathVariable(value = "id", required = true) Long id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/update/{id}")
    public Device update(@PathVariable(value = "id", required = true) Long id, @RequestBody Device requestDevice) {
        return requestDevice;
    }
}
