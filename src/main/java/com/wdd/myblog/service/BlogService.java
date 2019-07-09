package com.wdd.myblog.service;

import com.wdd.myblog.entity.Blog;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;

/**
 * @Classname BlogService
 * @Description None
 * @Date 2019/7/8 14:19
 * @Created by WDD
 */
public interface BlogService {
    String saveBlog(Blog blog);

    PageResult getBlogsPage(PageQueryUtil pageUtil);
}
