package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname BlogTagRelationMapper
 * @Description None
 * @Date 2019/7/9 7:49
 * @Created by WDD
 */
public interface BlogTagRelationMapper {
    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);

    void deleteByBlogId(Long blogId);

    List<Long> selectDistinctTagIds(Integer[] ids);
}
