package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.Device;

/**
*
*/
public interface DeviceMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Device entity);

    int insertSelective(Device entity);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device entity);

    int updateByPrimaryKey(Device entity);

}
