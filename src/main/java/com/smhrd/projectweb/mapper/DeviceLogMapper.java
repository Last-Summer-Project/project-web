package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.DeviceLog;

import java.util.List;

/**
*/
public interface DeviceLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DeviceLog entity);

    int insertSelective(DeviceLog entity);

    DeviceLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceLog entity);

    int updateByPrimaryKey(DeviceLog entity);

    List<DeviceLog> selectByDeviceId(Long id);

    DeviceLog selectLatestByDeviceId(Long id);

}
