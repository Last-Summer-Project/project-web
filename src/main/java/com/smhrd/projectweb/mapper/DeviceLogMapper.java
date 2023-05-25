package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.DeviceLog;

import java.util.List;

/**
 *
 */
public interface DeviceLogMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(DeviceLog entity);

    Long insertSelective(DeviceLog entity);

    DeviceLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceLog entity);

    int updateByPrimaryKey(DeviceLog entity);

    List<DeviceLog> selectByDeviceId(Long id);

    DeviceLog selectLatestByDeviceId(Long id);

    DeviceLog selectLatestDetectedByDeviceId(Long id);
}
