package com.wdd.myblog.controller.admin;

import com.wdd.myblog.service.BlogTagService;
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
 * @Classname TagController
 * @Description None
 * @Date 2019/7/10 7:45
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private BlogTagService blogTagService;


    /**
     * 跳转标签页面
     * @param request
     * @return
     */
    @GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }


    /**
     * 异步加载标签列表数据
     * @param params
     * @return
     */
    @GetMapping("/tags/list")
    @ResponseBody
    public AjaxResult<Object> list(@RequestParam Map<String, Object> params) {
        AjaxResult<Object> ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult tagsPage = blogTagService.getBlogTagPage(pageUtil);
        ajaxResult.setData(tagsPage);
        return ajaxResult;
    }


    /**
     * 新增标签
     * @param tagName
     * @return
     */
    @PostMapping("/tags/save")
    @ResponseBody
    public AjaxResult save(@RequestParam("tagName") String tagName) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(tagName)) {
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogTagService.saveTag(tagName)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("标签名称重复");
        }
        return ajaxResult;
    }

    /**
     * 删除标签
     * @param ids
     * @return
     */
    @PostMapping("/tags/delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody Integer[] ids) {
        AjaxResult ajaxResult = new AjaxResult();
        if (ids.length < 1) {
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogTagService.deleteBatch(ids)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("有关联数据请勿强行删除");
        }
        return ajaxResult;
    }
}
