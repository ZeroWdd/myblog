package com.wdd.myblog.controller.admin;

import com.wdd.myblog.entity.BlogCategory;
import com.wdd.myblog.service.BlogCategoryService;
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
 * @Classname CategoryController
 * @Description None
 * @Date 2019/7/10 8:30
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    /**
     * 跳转博客分类页面
     * @param request
     * @return
     */
    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    /**
     * 异步加载分类数据
     * @param params
     * @return
     */
    @GetMapping("/categories/list")
    @ResponseBody
    public AjaxResult<Object> list(@RequestParam Map<String, Object> params) {
        AjaxResult<Object> ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult categoriesPage = blogCategoryService.getBlogCategoryPage(pageUtil);
        ajaxResult.setData(categoriesPage);
        return ajaxResult;
    }


    /**
     * 分类添加
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @PostMapping("/categories/save")
    @ResponseBody
    public AjaxResult save(@RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon) {
        AjaxResult ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(categoryName) || StringUtils.isEmpty(categoryIcon)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogCategoryService.saveCategory(categoryName, categoryIcon)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("分类名称重复");
        }
        return ajaxResult;
    }


    /**
     * 分类修改
     * @param categoryId
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @RequestMapping("/categories/update")
    @ResponseBody
    public AjaxResult update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon) {
        AjaxResult ajaxResult = new AjaxResult<>();
        if (StringUtils.isEmpty(categoryName) || StringUtils.isEmpty(categoryIcon)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogCategoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("分类名称重复");
        }
        return ajaxResult;
    }

    /**
     * 修改分类回显
     * @param id
     * @return
     */
    @RequestMapping("/categories/echo")
    @ResponseBody
    public AjaxResult update(Integer id) {
        AjaxResult ajaxResult = new AjaxResult();
        BlogCategory blogCategory = blogCategoryService.getBlogCategoryById(id);
        if(blogCategory != null){
            ajaxResult.setSuccess(true);
            ajaxResult.setData(blogCategory);
        }else{
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("数据异常");
        }
        return ajaxResult;
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @PostMapping("/categories/delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody Integer[] ids) {
        AjaxResult ajaxResult = new AjaxResult();
        if (ids.length < 1) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("参数异常！");
            return ajaxResult;
        }
        if (blogCategoryService.deleteBatch(ids)) {
            ajaxResult.setSuccess(true);
        } else {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }

}
