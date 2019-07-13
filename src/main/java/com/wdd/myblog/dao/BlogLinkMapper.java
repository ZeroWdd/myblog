package com.wdd.myblog.dao;

import com.wdd.myblog.entity.BlogLink;
import com.wdd.myblog.util.PageQueryUtil;

import java.util.List;

/**
 * @Classname BlogLinkMapper
 * @Description None
 * @Date 2019/7/13 17:09
 * @Created by WDD
 */
public interface BlogLinkMapper {
    List<BlogLink> findLinkList(PageQueryUtil pageUtil);

    int getTotalLinks(PageQueryUtil pageUtil);

    int insertSelective(BlogLink blogLink);

    BlogLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogLink blogLink);

    int deleteBatch(Integer[] ids);
}
