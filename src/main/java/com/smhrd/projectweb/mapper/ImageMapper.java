package com.smhrd.projectweb.mapper;

import com.smhrd.projectweb.entity.sql.Image;

/**
*/
public interface ImageMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(Image entity);

    Long insertSelective(Image entity);

    Image selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Image entity);

    int updateByPrimaryKey(Image entity);

}
