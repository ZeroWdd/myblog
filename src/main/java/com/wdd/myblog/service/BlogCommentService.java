package com.wdd.myblog.service;

import com.wdd.myblog.entity.BlogComment;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;

/**
 * @Classname BlogCommentService
 * @Description None
 * @Date 2019/7/12 22:34
 * @Created by WDD
 */
public interface BlogCommentService {
    PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page);

    int getTotalComments();


    PageResult getCommentsPage(PageQueryUtil pageUtil);

    boolean checkDone(Integer[] ids);

    boolean deleteBatch(Integer[] ids);

    boolean reply(Long commentId, String replyBody);

    boolean addComment(BlogComment comment);
}
