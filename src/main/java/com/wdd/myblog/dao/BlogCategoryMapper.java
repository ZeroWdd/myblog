package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogCategory;

import java.util.List;

/**
 * @Classname BlogCategoryMapper
 * @Description None
 * @Date 2019/7/7 16:53
 * @Created by WDD
 */
public interface BlogCategoryMapper {
    List<BlogCategory> findCategoryList();

}
