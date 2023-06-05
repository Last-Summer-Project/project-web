package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.DeviceLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 */
@Mapper
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

    List<DeviceLog> selectDetectedByDeviceIdPerDay(Long id);
}
