package com.wdd.myblog.controller.common;

import com.wdd.myblog.util.Const;
import com.wdd.myblog.util.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Classname CommonController
 * @Description None
 * @Date 2019/7/7 11:24
 * @Created by WDD
 */
@Controller
public class CommonController {


    /**
     * 显示 验证码
     * @param request
     * @param response
     * @param vl
     * @param w
     * @param h
     */
    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
                               @RequestParam(value="w",defaultValue="110",required=false) Integer w,
                               @RequestParam(value="h",defaultValue="39",required=false) Integer h){
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
