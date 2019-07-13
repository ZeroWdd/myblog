package com.wdd.myblog.controller.admin;

import com.wdd.myblog.entity.BlogLink;
import com.wdd.myblog.service.BlogLinkService;
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
 * @Classname LinkController
 * @Description None
 * @Date 2019/7/13 17:02
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class LinkController {

    @Autowired
    private BlogLinkService blogLinkService;

    /**
     * 跳转友链界面
     * @param request
     * @return
     */
    @GetMapping("/links")
    public String linkPage(HttpServletRequest request) {
        request.setAttribute("path", "links");
        return "admin/link";
    }

    /**
     * 异步加载友链数据
     * @param params
     * @return
     */
    @GetMapping("/links/list")
    @ResponseBody
    public AjaxResult<Object> list(@RequestParam Map<String, Object> params) {
        AjaxResult<Object> ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常");
            return ajaxResult;
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult blogLinksPage = blogLinkService.getBlogLinkPage(pageUtil);
        ajaxResult.setData(blogLinksPage);
        return ajaxResult;
    }

    /**
     * 友链添加
     * @param blogLink
     * @return
     */
    @RequestMapping(value = "/links/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(BlogLink blogLink) {
        AjaxResult ajaxResult = new AjaxResult();
        int count = blogLinkService.saveLink(blogLink);
        if(count > 0){
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("添加成功");
        }else{
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("添加失败");
        }
        return ajaxResult;
    }


    @RequestMapping(value = "/links/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(BlogLink blogLink) {
        AjaxResult ajaxResult = new AjaxResult();
        int count = blogLinkService.updateLink(blogLink);
        if(count > 0){
            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }


    /**
     * 友链回显
     * @param id
     * @return
     */
    @GetMapping("/links/info/{id}")
    @ResponseBody
    public AjaxResult<Object> info(@PathVariable("id") Integer id) {
        AjaxResult<Object> ajaxResult = new AjaxResult<>();
        BlogLink link = blogLinkService.selectById(id);
        if (link != null){
            ajaxResult.setSuccess(true);
            ajaxResult.setData(link);
        }else{
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 删除友链
     * @param ids
     * @return
     */
    @RequestMapping(value = "/links/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delete(@RequestBody Integer[] ids) {
        AjaxResult ajaxResult = new AjaxResult();
        if (ids.length < 1) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("数据异常");
        }
        if (blogLinkService.deleteBatch(ids)) {
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("数据异常");
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }
}
