package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogCategory;
import com.wdd.myblog.util.PageQueryUtil;

import java.util.List;

/**
 * @Classname BlogCategoryMapper
 * @Description None
 * @Date 2019/7/7 16:53
 * @Created by WDD
 */
public interface BlogCategoryMapper {

    BlogCategory selectByPrimaryKey(Integer blogCategoryId);

    int updateByPrimaryKeySelective(BlogCategory blogCategory);

    List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

    int getTotalCategories(PageQueryUtil pageUtil);

    BlogCategory selectByCategoryName(String categoryName);

    int insertSelective(BlogCategory record);

    int getBlogCategoryById(Integer id);
}
