package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.Device;
import org.apache.ibatis.annotations.Mapper;

/**
*
*/
@Mapper
public interface DeviceMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(Device entity);

    Long insertSelective(Device entity);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device entity);

    int updateByPrimaryKey(Device entity);

    Device selectByLoginId(String loginId);

}
