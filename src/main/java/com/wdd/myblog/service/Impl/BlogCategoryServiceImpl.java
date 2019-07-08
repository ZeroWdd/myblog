package com.wdd.myblog.service.Impl;

import com.wdd.myblog.dao.BlogCategoryMapper;
import com.wdd.myblog.entity.BlogCategory;
import com.wdd.myblog.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname CategoryServiceImpl
 * @Description None
 * @Date 2019/7/7 16:10
 * @Created by WDD
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList();
    }
}
