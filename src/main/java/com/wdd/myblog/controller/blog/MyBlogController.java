package com.wdd.myblog.controller.blog;

import com.wdd.myblog.service.BlogCommentService;
import com.wdd.myblog.service.BlogConfigService;
import com.wdd.myblog.service.BlogService;
import com.wdd.myblog.service.BlogTagService;
import com.wdd.myblog.util.PageResult;
import com.wdd.myblog.vo.BlogDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname MyBlogController
 * @Description None
 * @Date 2019/7/10 19:05
 * @Created by WDD
 */
@Controller
public class MyBlogController {

    public static String theme = "amaze";

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogConfigService blogConfigService;
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 博客首页
     * @param request
     * @return
     */
    @GetMapping({"/"})
    public String index(HttpServletRequest request) {
        return this.page(request, 1);
    }

    /**
     * 首页 分页数据
     * @param request
     * @param pageNum
     * @return
     */
    @GetMapping({"/page/{pageNum}"})
    public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
        PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
        if (blogPageResult == null) {
            return "error/error_404";
        }
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("pageName", "首页");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/index";
    }


    /**
     * 搜索列表页
     * @param request
     * @param keyword
     * @return
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, keyword, 1);
    }

    @GetMapping({"/search/{keyword}/{page}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageBySearch(keyword, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "搜索");
        request.setAttribute("pageUrl", "search");
        request.setAttribute("keyword", keyword);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/list";
    }


    /**
     * 标签列表页
     * @param request
     * @param tagName
     * @return
     */
    @GetMapping({"/tag/{tagName}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName) {
        return tag(request, tagName, 1);
    }

    @GetMapping({"/tag/{tagName}/{page}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageByTag(tagName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "标签");
        request.setAttribute("pageUrl", "tag");
        request.setAttribute("keyword", tagName);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/list";
    }


    /**
     * 详情页
     * @param request
     * @param blogId
     * @param commentPage
     * @return
     */
    @GetMapping({"/blog/{blogId}", "/article/{blogId}"})
    public String detail(HttpServletRequest request, @PathVariable("blogId") Long blogId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {
        BlogDetailVO blogDetailVO = blogService.getBlogDetail(blogId);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);
            request.setAttribute("commentPageResult", blogCommentService.getCommentPageByBlogIdAndPageNum(blogId, commentPage));
        }
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/detail";
    }


    /**
     * 分类列表页
     * @param request
     * @param categoryName
     * @return
     */
    @GetMapping({"/category/{categoryName}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName) {
        return category(request, categoryName, 1);
    }

    @GetMapping({"/category/{categoryName}/{page}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageByCategory(categoryName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "分类");
        request.setAttribute("pageUrl", "category");
        request.setAttribute("keyword", categoryName);
        request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
        request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/list";
    }
}
