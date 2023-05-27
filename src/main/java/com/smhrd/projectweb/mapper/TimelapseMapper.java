package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.Timelapse;
import org.apache.ibatis.annotations.Mapper;

/**
*
*/
@Mapper
public interface TimelapseMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(Timelapse entity);

    Long insertSelective(Timelapse entity);

    Timelapse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Timelapse entity);

    int updateByPrimaryKey(Timelapse entity);

}
