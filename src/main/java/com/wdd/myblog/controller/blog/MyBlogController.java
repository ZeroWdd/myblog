package com.wdd.myblog.controller.blog;

import com.wdd.myblog.entity.BlogComment;
import com.wdd.myblog.entity.BlogLink;
import com.wdd.myblog.service.*;
import com.wdd.myblog.util.*;
import com.wdd.myblog.vo.BlogDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private BlogLinkService blogLinkService;

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

    /**
     * 友情链接页面
     * @param request
     * @return
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Byte, List<BlogLink>> linkMap = blogLinkService.getLinksForLinkPage();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey((byte) 0)) {
                request.setAttribute("favoriteLinks", linkMap.get((byte) 0));
            }
            if (linkMap.containsKey((byte) 1)) {
                request.setAttribute("recommendLinks", linkMap.get((byte) 1));
            }
            if (linkMap.containsKey((byte) 2)) {
                request.setAttribute("personalLinks", linkMap.get((byte) 2));
            }
        }
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/link";
    }

    /**
     * 评论操作
     * @param request
     * @param session
     * @param blogId
     * @param verifyCode
     * @param commentator
     * @param email
     * @param websiteUrl
     * @param commentBody
     * @return
     */
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public AjaxResult comment(HttpServletRequest request, HttpSession session,
                              @RequestParam Long blogId, @RequestParam String verifyCode,
                              @RequestParam String commentator, @RequestParam String email,
                              @RequestParam String websiteUrl, @RequestParam String commentBody) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(verifyCode)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("验证码不能为空");
            return  ajaxResult;
        }
        String code = (String) session.getAttribute(Const.CODE);
        if (StringUtils.isEmpty(code)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("非法请求");
            return  ajaxResult;
        }
        if (!verifyCode.equalsIgnoreCase(code)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("验证码错误");
            return  ajaxResult;
        }
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("非法请求");
            return  ajaxResult;
        }
        if (null == blogId || blogId < 0) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("非法请求");
            return  ajaxResult;
        }
        if (StringUtils.isEmpty(commentator)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入称呼");
            return  ajaxResult;
        }
        if (StringUtils.isEmpty(email)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入邮箱地址");
            return  ajaxResult;
        }
        if (!PatternUtil.isEmail(email)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入正确的邮箱地址");
            return  ajaxResult;
        }
        if (StringUtils.isEmpty(commentBody)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请输入评论内容");
            return  ajaxResult;
        }
        if (commentBody.trim().length() > 200) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("评论内容过长");
            return  ajaxResult;
        }
        BlogComment comment = new BlogComment();
        comment.setBlogId(blogId);
        comment.setCommentator(MyBlogUtils.cleanString(commentator));
        comment.setEmail(email);
        if (PatternUtil.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }
        comment.setCommentBody(MyBlogUtils.cleanString(commentBody));
        if(blogCommentService.addComment(comment)){
            ajaxResult.setSuccess(true);
        }
        return ajaxResult;
    }
}
