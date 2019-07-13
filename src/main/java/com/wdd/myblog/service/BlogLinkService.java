package com.wdd.myblog.service;

import com.wdd.myblog.entity.BlogLink;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;

/**
 * @Classname BlogLinkService
 * @Description None
 * @Date 2019/7/13 17:08
 * @Created by WDD
 */
public interface BlogLinkService {
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    int saveLink(BlogLink blogLink);

    BlogLink selectById(Integer id);

    int updateLink(BlogLink blogLink);

    boolean deleteBatch(Integer[] ids);
}
