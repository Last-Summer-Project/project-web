package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.Timelapse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<Timelapse> selectByDeviceId(Long id);

    Timelapse selectLatestByDeviceId(Long id);

    Timelapse selectLatestDoneByDeviceId(Long id);
}
