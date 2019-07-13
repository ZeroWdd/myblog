package com.wdd.myblog.service;

import com.wdd.myblog.entity.BlogTagCount;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;

import java.util.List;

/**
 * @Classname BlogTagService
 * @Description None
 * @Date 2019/7/10 7:59
 * @Created by WDD
 */
public interface BlogTagService {
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    boolean saveTag(String tagName);

    boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();

}
