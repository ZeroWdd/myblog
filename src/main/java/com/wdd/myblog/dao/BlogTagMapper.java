package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogTag;

import java.util.List;

/**
 * @Classname BlogTagMapper
 * @Description None
 * @Date 2019/7/8 19:41
 * @Created by WDD
 */
public interface BlogTagMapper {
    BlogTag selectByTagName(String tag);

    void batchInsertBlogTag(List<BlogTag> tagListForInsert);
}
