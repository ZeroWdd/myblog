package com.wdd.myblog.service.Impl;

import com.wdd.myblog.dao.AdminUserMapper;
import com.wdd.myblog.entity.AdminUser;
import com.wdd.myblog.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname AdminUserServiceImpl
 * @Description None
 * @Date 2019/7/7 13:54
 * @Created by WDD
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName,password);
    }
}
