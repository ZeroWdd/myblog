package com.wdd.myblog.service;

import com.wdd.myblog.entity.Blog;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;
import com.wdd.myblog.vo.BlogDetailVO;
import com.wdd.myblog.vo.BlogListVO;

import java.util.List;

/**
 * @Classname BlogService
 * @Description None
 * @Date 2019/7/8 14:19
 * @Created by WDD
 */
public interface BlogService {
    String saveBlog(Blog blog);

    PageResult getBlogsPage(PageQueryUtil pageUtil);

    Blog getBlogById(Long blogId);

    String updateBlog(Blog blog);

    boolean deleteBatch(Integer[] ids);

    PageResult getBlogsForIndexPage(int pageNum);

    List<BlogListVO> getBlogListForIndexPage(int i);

    PageResult getBlogsPageBySearch(String keyword, Integer page);

    PageResult getBlogsPageByTag(String tagName, Integer page);

    BlogDetailVO getBlogDetail(Long blogId);

    PageResult getBlogsPageByCategory(String categoryName, Integer page);

    int getTotalBlogs();
}
