package com.wdd.myblog.service;

/**
 * @Classname BlogCommentService
 * @Description None
 * @Date 2019/7/12 22:34
 * @Created by WDD
 */
public interface BlogCommentService {
    Object getCommentPageByBlogIdAndPageNum(Long blogId, int page);
}
