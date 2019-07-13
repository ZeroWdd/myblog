package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogConfig;

import java.util.List;

/**
 * @Classname BlogConfigMapper
 * @Description None
 * @Date 2019/7/10 19:38
 * @Created by WDD
 */
public interface BlogConfigMapper {
    List<BlogConfig> selectAll();

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig blogConfig);
}
