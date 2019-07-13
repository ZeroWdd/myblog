package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogTag;
import com.wdd.myblog.entity.BlogTagCount;
import com.wdd.myblog.util.PageQueryUtil;

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

    List<BlogTag> findTagList(PageQueryUtil pageUtil);

    int getTotalTags(PageQueryUtil pageUtil);

    int insertSelective(BlogTag record);

    int deleteBatch(Integer[] ids);

    List<BlogTagCount> getTagCount();
}
