package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.Timelapse;

/**
*
*/
public interface TimelapseMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(Timelapse entity);

    Long insertSelective(Timelapse entity);

    Timelapse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Timelapse entity);

    int updateByPrimaryKey(Timelapse entity);

}
