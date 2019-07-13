package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogComment;
import com.wdd.myblog.util.PageQueryUtil;

import java.util.List;
import java.util.Map;

/**
 * @Classname BlogCommentMapper
 * @Description None
 * @Date 2019/7/12 22:31
 * @Created by WDD
 */
public interface BlogCommentMapper {
    Integer getTotalBlogComments(Map params);

    List<BlogComment> findBlogCommentList(PageQueryUtil pageUtil);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment blogComment);

    int insertSelective(BlogComment comment);
}
