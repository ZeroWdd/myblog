package com.wdd.myblog.controller.admin;

import com.wdd.myblog.entity.Blog;
import com.wdd.myblog.service.BlogCategoryService;
import com.wdd.myblog.service.BlogService;
import com.wdd.myblog.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

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
     * 跳转博客首页
     * @param request
     * @return
     */
    @GetMapping("/blogs")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "blogs");
        return "admin/blog";
    }

    /**
     * 异步加载博客数据列表
     * @param params
     * @return
     */
    @GetMapping("/blogs/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> params) {
        AjaxResult<Object> ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult blogsPage = blogService.getBlogsPage(pageUtil);
        ajaxResult.setData(blogsPage);
        return ajaxResult;
    }

    /**
     * 跳转编辑添加博客页面
     * @param request
     * @return
     */
    @GetMapping("/blogs/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute(Const.PATH, "edit");
        request.setAttribute("categories", blogCategoryService.getAllCategories());
        return "admin/edit";
    }

    /**
     * 跳转编辑修改博客页面
     * @param request
     * @param blogId
     * @return
     */
    @GetMapping("/blogs/edit/{blogId}")
    public String edit(HttpServletRequest request, @PathVariable("blogId") Long blogId) {
        request.setAttribute("path", "edit");
        Blog blog = blogService.getBlogById(blogId);
        if (blog == null) {
            return "error/error_400";
        }
        request.setAttribute("blog", blog);
        request.setAttribute("categories", blogCategoryService.getAllCategories());
        return "admin/edit";
    }

    /**
     * 保存博客
     * @param blog
     * @return
     */
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
        String saveBlogResult = blogService.saveBlog(blog);
        if ("success".equals(saveBlogResult)) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage(saveBlogResult);
        }
        return ajaxResult;
    }


    /**
     * 修改博客
     * @param blog
     * @return
     */
    @PostMapping("/blogs/update")
    @ResponseBody
    public AjaxResult update(Blog blog) {
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
        String updateBlogResult = blogService.updateBlog(blog);
        if ("success".equals(updateBlogResult)) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("修改成功");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage(updateBlogResult);
        }
        return ajaxResult;
    }


    /**
     * 删除博客
     * @param ids
     * @return
     */
    @PostMapping("/blogs/delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody Integer[] ids) {
        AjaxResult ajaxResult = new AjaxResult();
        if (ids.length < 1) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        File fileDir = UploadUtil.getImgBlogCover();
        for(Integer id : ids){
            Blog byId = blogService.getBlogById(Long.valueOf(id));
            if(!byId.getBlogCoverImage().isEmpty()){
                //获取图片名称
                String img = byId.getBlogCoverImage().split("/")[byId.getBlogCoverImage().split("/").length - 1];
                File file = new File(fileDir.getAbsolutePath() + File.separator + img);
                if(file != null){
                    file.delete();
                }
            }
        }
        if (blogService.deleteBatch(ids)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败！");
        }
        return ajaxResult;
    }
}
