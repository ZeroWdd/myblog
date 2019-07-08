package com.wdd.myblog.dao;

import com.wdd.myblog.entity.AdminUser;

/**
 * @Classname AdminUserMapper
 * @Description None
 * @Date 2019/7/7 13:56
 * @Created by WDD
 */
public interface AdminUserMapper {
    AdminUser login(String userName, String password);
}
