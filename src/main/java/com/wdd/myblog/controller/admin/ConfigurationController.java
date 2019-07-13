package com.wdd.myblog.controller.admin;

import com.wdd.myblog.service.BlogConfigService;
import com.wdd.myblog.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ConfigurationController
 * @Description None
 * @Date 2019/7/13 15:55
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class ConfigurationController {

    @Autowired
    private BlogConfigService blogConfigService;

    /**
     * 跳转体用配置页面
     * @param request
     * @return
     */
    @GetMapping("/configurations")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "configurations");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "admin/configuration";
    }


    /**
     * 修改站点信息
     * @param websiteName
     * @param websiteDescription
     * @param websiteLogo
     * @param websiteIcon
     * @return
     */
    @PostMapping("/configurations/website")
    @ResponseBody
    public AjaxResult website(@RequestParam(value = "websiteName", required = false) String websiteName,
                              @RequestParam(value = "websiteDescription", required = false) String websiteDescription,
                              @RequestParam(value = "websiteLogo", required = false) String websiteLogo,
                              @RequestParam(value = "websiteIcon", required = false) String websiteIcon) {
        AjaxResult ajaxResult = new AjaxResult();
        int updateResult = 0;
        if (!StringUtils.isEmpty(websiteName)) {
            updateResult += blogConfigService.updateConfig("websiteName", websiteName);
        }
        if (!StringUtils.isEmpty(websiteDescription)) {
            updateResult += blogConfigService.updateConfig("websiteDescription", websiteDescription);
        }
        if (!StringUtils.isEmpty(websiteLogo)) {
            updateResult += blogConfigService.updateConfig("websiteLogo", websiteLogo);
        }
        if (!StringUtils.isEmpty(websiteIcon)) {
            updateResult += blogConfigService.updateConfig("websiteIcon", websiteIcon);
        }
        if (updateResult == 4){
            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 修改个人信息
     * @param yourAvatar
     * @param yourName
     * @param yourEmail
     * @return
     */
    @PostMapping("/configurations/userInfo")
    @ResponseBody
    public AjaxResult userInfo(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
                           @RequestParam(value = "yourName", required = false) String yourName,
                           @RequestParam(value = "yourEmail", required = false) String yourEmail) {
        AjaxResult ajaxResult = new AjaxResult();
        int updateResult = 0;
        if (!StringUtils.isEmpty(yourAvatar)) {
            updateResult += blogConfigService.updateConfig("yourAvatar", yourAvatar);
        }
        if (!StringUtils.isEmpty(yourName)) {
            updateResult += blogConfigService.updateConfig("yourName", yourName);
        }
        if (!StringUtils.isEmpty(yourEmail)) {
            updateResult += blogConfigService.updateConfig("yourEmail", yourEmail);
        }
        if (updateResult == 3){
            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    /**
     * 修改底部设置
     * @param footerAbout
     * @param footerICP
     * @param footerCopyRight
     * @param footerPoweredBy
     * @param footerPoweredByURL
     * @return
     */
    @PostMapping("/configurations/footer")
    @ResponseBody
    public AjaxResult footer(@RequestParam(value = "footerAbout", required = false) String footerAbout,
                         @RequestParam(value = "footerICP", required = false) String footerICP,
                         @RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
                         @RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
                         @RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL) {
        AjaxResult ajaxResult = new AjaxResult();
        int updateResult = 0;
        if (!StringUtils.isEmpty(footerAbout)) {
            updateResult += blogConfigService.updateConfig("footerAbout", footerAbout);
        }
        if (!StringUtils.isEmpty(footerICP)) {
            updateResult += blogConfigService.updateConfig("footerICP", footerICP);
        }
        if (!StringUtils.isEmpty(footerCopyRight)) {
            updateResult += blogConfigService.updateConfig("footerCopyRight", footerCopyRight);
        }
        if (!StringUtils.isEmpty(footerPoweredBy)) {
            updateResult += blogConfigService.updateConfig("footerPoweredBy", footerPoweredBy);
        }
        if (!StringUtils.isEmpty(footerPoweredByURL)) {
            updateResult += blogConfigService.updateConfig("footerPoweredByURL", footerPoweredByURL);
        }
        if (updateResult == 5){
            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }
}
