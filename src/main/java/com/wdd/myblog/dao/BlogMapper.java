package com.wdd.myblog.dao;

import com.wdd.myblog.entity.Blog;
import com.wdd.myblog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname BlogMapper
 * @Description None
 * @Date 2019/7/8 18:17
 * @Created by WDD
 */
public interface BlogMapper {
    int insertSelective(Blog record);

    List<Blog> findBlogList(PageQueryUtil pageUtil);

    int getTotalBlogs(PageQueryUtil pageUtil);

    Blog selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(Blog record);

    int deleteBatch(Integer[] ids);

    void updateBlogCategorys(String categoryName, Integer categoryId, Integer[] ids);

    List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);

    List<Blog> getBlogsPageByTagId(PageQueryUtil pageUtil);

    int getTotalBlogsByTagId(PageQueryUtil pageUtil);

    void updateByPrimaryKey(Blog blog);
}
