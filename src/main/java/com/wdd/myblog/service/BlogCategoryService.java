package com.wdd.myblog.service;

import com.wdd.myblog.entity.BlogCategory;

import java.util.List;

/**
 * @Classname BlogCategoryService
 * @Description None
 * @Date 2019/7/7 16:10
 * @Created by WDD
 */
public interface BlogCategoryService {
    List<BlogCategory> getAllCategories();
}
