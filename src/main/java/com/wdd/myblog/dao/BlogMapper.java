package com.wdd.myblog.dao;

import com.wdd.myblog.entity.Blog;

/**
 * @Classname BlogMapper
 * @Description None
 * @Date 2019/7/8 18:17
 * @Created by WDD
 */
public interface BlogMapper {
    int insertSelective(Blog record);
}
