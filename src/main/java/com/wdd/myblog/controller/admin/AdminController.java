package com.wdd.myblog.controller.admin;

import com.wdd.myblog.entity.AdminUser;
import com.wdd.myblog.service.*;
import com.wdd.myblog.util.AjaxResult;
import com.wdd.myblog.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Classname AdminController
 * @Description None
 * @Date 2019/7/7 10:57
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 登录界面
     * @return
     */
    @GetMapping({"/login"})
    public String login(){
        return "/admin/login";
    }


    /**
     * 提交登录表单
     * @param userName
     * @param password
     * @param verifyCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            @RequestParam("verifyCode") String verifyCode,
                            HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(verifyCode)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("验证码不能为空");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("用户名或密码不能为空");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(session.getAttribute(Const.CODE)) || !verifyCode.equalsIgnoreCase((String) session.getAttribute(Const.CODE))) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("验证码错误");
            return ajaxResult;
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute(Const.ADMINUSER,adminUser);
            //session过期时间设置为7200秒 即两小时
            session.setMaxInactiveInterval(60 * 60 * 2);

            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("登录成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("用户名或密码错误");
        }
        return ajaxResult;
    }

    @GetMapping({"/index"})
    public String index(HttpServletRequest request) {
        request.setAttribute("categoryCount", blogCategoryService.getTotalCategories());
        request.setAttribute("blogCount", blogService.getTotalBlogs());
//        request.setAttribute("linkCount", linkService.getTotalLinks());
        request.setAttribute("tagCount", blogTagService.getTotalTags());
        request.setAttribute("commentCount", blogCommentService.getTotalComments());
        request.setAttribute(Const.PATH, "index");
        return "admin/index";
    }

}
