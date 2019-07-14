package com.wdd.myblog.service.Impl;

import com.wdd.myblog.dao.BlogLinkMapper;
import com.wdd.myblog.entity.BlogLink;
import com.wdd.myblog.service.BlogLinkService;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname BlogLinkServiceImpl
 * @Description None
 * @Date 2019/7/13 17:08
 * @Created by WDD
 */
@Service
public class BlogLinkServiceImpl implements BlogLinkService {

    @Autowired
    private BlogLinkMapper blogLinkMapper;

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<BlogLink> links = blogLinkMapper.findLinkList(pageUtil);
        int total = blogLinkMapper.getTotalLinks(pageUtil);
        PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int saveLink(BlogLink blogLink) {
        return blogLinkMapper.insertSelective(blogLink);
    }

    @Override
    public BlogLink selectById(Integer id) {
        return blogLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateLink(BlogLink blogLink) {
        return blogLinkMapper.updateByPrimaryKeySelective(blogLink);
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        return blogLinkMapper.deleteBatch(ids) > 0;
    }

    @Override
    public Map<Byte, List<BlogLink>> getLinksForLinkPage() {
        //获取所有链接数据
        List<BlogLink> links = blogLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(links)) {
            //根据type进行分组
            Map<Byte, List<BlogLink>> linksMap = links.stream().collect(Collectors.groupingBy(BlogLink::getLinkType));
            return linksMap;
        }
        return null;
    }

    @Override
    public int getTotalLinks() {
        return blogLinkMapper.getTotalLinks(null);
    }
}
