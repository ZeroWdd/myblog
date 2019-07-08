package com.wdd.myblog.controller.admin;

import com.wdd.myblog.entity.Blog;
import com.wdd.myblog.service.BlogCategoryService;
import com.wdd.myblog.service.BlogService;
import com.wdd.myblog.util.AjaxResult;
import com.wdd.myblog.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname BlogController
 * @Description None
 * @Date 2019/7/7 16:00
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    /**
     * 编辑修改博客
     * @param request
     * @return
     */
    @GetMapping("/blogs/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute(Const.PATH, "edit");
        request.setAttribute("categories", blogCategoryService.getAllCategories());
        return "admin/edit";
    }

    @PostMapping("/blogs/save")
    @ResponseBody
    public AjaxResult save(Blog blog) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(blog.getBlogTitle())) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入文章标题");
            return ajaxResult;
        }
        if (blog.getBlogTitle().trim().length() > 150) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("标题过长");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(blog.getBlogTags())) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入文章标签");
            return ajaxResult;
        }
        if (blog.getBlogTags().trim().length() > 150) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("标签过长");
            return ajaxResult;
        }
        if (blog.getBlogSubUrl().trim().length() > 150) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("路径过长");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(blog.getBlogContent())) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入文章内容");
            return ajaxResult;
        }
        if (blog.getBlogContent().trim().length() > 100000) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("文章内容过长");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(blog.getBlogCoverImage())) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("封面图不能为空");
            return ajaxResult;
        }
//        String saveBlogResult = blogService.saveBlog(blog);
//        if ("success".equals(saveBlogResult)) {
//            return ResultGenerator.genSuccessResult("添加成功");
//        } else {
//            return ResultGenerator.genFailResult(saveBlogResult);
//        }
        return ajaxResult;
    }
}
