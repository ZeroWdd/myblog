package com.wdd.myblog.controller.admin;

import com.wdd.myblog.service.BlogCommentService;
import com.wdd.myblog.util.AjaxResult;
import com.wdd.myblog.util.PageQueryUtil;
import com.wdd.myblog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Classname CommentController
 * @Description None
 * @Date 2019/7/13 10:23
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 跳转登录页面
     * @param request
     * @return
     */
    @GetMapping("/comments")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "comments");
        return "admin/comment";
    }

    /**
     * 异步加载评论数据列表
     * @param params
     * @return
     */
    @GetMapping("/comments/list")
    @ResponseBody
    public AjaxResult<Object> list(@RequestParam Map<String, Object> params) {
        AjaxResult<Object> ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult commentsPage = blogCommentService.getCommentsPage(pageUtil);
        ajaxResult.setData(commentsPage);
        return ajaxResult;
    }

    /**
     * 批量审核
     * @param ids
     * @return
     */
    @PostMapping("/comments/checkDone")
    @ResponseBody
    public AjaxResult checkDone(@RequestBody Integer[] ids) {
        AjaxResult ajaxResult = new AjaxResult();
        if (ids.length < 1) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogCommentService.checkDone(ids)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("审核失败");
        }
        return ajaxResult;
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/comments/delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody Integer[] ids) {
        AjaxResult ajaxResult = new AjaxResult();
        if (ids.length < 1) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogCommentService.deleteBatch(ids)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("审核失败");
        }
        return ajaxResult;
    }


    /**
     * 回复功能实现
     * @param commentId
     * @param replyBody
     * @return
     */
    @PostMapping("/comments/reply")
    @ResponseBody
    public AjaxResult checkDone(@RequestParam("commentId") Long commentId,
                            @RequestParam("replyBody") String replyBody) {
        AjaxResult ajaxResult = new AjaxResult();
        if (commentId == null || commentId < 1 || StringUtils.isEmpty(replyBody)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogCommentService.reply(commentId, replyBody)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("审核失败");
        }
        return ajaxResult;
    }
}
