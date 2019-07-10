package com.wdd.myblog.service;

import com.wdd.myblog.entity.BlogCategory;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;

import java.util.List;

/**
 * @Classname BlogCategoryService
 * @Description None
 * @Date 2019/7/7 16:10
 * @Created by WDD
 */
public interface BlogCategoryService {
    List<BlogCategory> getAllCategories();

    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    boolean saveCategory(String categoryName, String categoryIcon);

    boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    BlogCategory getBlogCategoryById(Integer id);
}
