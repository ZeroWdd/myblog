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
    @Autowired
    private BlogLinkService blogLinkService;

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
        request.setAttribute("linkCount", blogLinkService.getTotalLinks());
        request.setAttribute("tagCount", blogTagService.getTotalTags());
        request.setAttribute("commentCount", blogCommentService.getTotalComments());
        request.setAttribute(Const.PATH, "index");
        return "admin/index";
    }

    /**
     * 跳转修改密码页面
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/profile")
    public String profile(HttpSession session,HttpServletRequest request) {
        AdminUser adminUser = (AdminUser)session.getAttribute(Const.ADMINUSER);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    /**
     * 修改密码
     * @param session
     * @param originalPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpSession session, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        AdminUser adminUser = (AdminUser)session.getAttribute(Const.ADMINUSER);
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        if (adminUserService.updatePassword(adminUser.getAdminUserId(), originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            session.invalidate();
            return "success";
        } else {
            return "修改失败";
        }
    }

    /**
     * 修改个人名称
     * @param request
     * @param loginUserName
     * @param nickName
     * @return
     */
    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpSession session,HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        AdminUser adminUser = (AdminUser)session.getAttribute(Const.ADMINUSER);
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return "参数不能为空";
        }
        if (adminUserService.updateName(adminUser.getAdminUserId(), loginUserName, nickName)) {
            return "success";
        } else {
            return "修改失败";
        }
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "admin/login";
    }

}
