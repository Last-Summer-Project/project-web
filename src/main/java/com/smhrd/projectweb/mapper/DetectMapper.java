package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.Detect;

/**
*
*/
public interface DetectMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(Detect entity);

    Long insertSelective(Detect entity);

    Detect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Detect entity);

    int updateByPrimaryKey(Detect entity);

}
