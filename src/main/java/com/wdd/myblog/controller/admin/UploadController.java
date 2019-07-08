package com.wdd.myblog.controller.admin;

import com.wdd.myblog.util.AjaxResult;
import com.wdd.myblog.util.MyBlogUtils;
import com.wdd.myblog.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Classname UploadController
 * @Description None
 * @Date 2019/7/7 20:30
 * @Created by WDD
 */
@Controller
@RequestMapping("/admin")
public class UploadController {

    @PostMapping({"/upload/file"})
    @ResponseBody
    public AjaxResult upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        AjaxResult ajaxResult = new AjaxResult();

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgBlogCover();

        // 构建真实的文件路径
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + newFileName);

        try {
            if (!newFile.exists()) {
                if (!newFile.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + newFile);
                }
            }
            file .transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imgUrl = MyBlogUtils.getHost(new URI(request.getRequestURL() + "")) + "/" +
                UploadUtil.IMG_BLOG_COVER + "/" +newFileName;
        ajaxResult.setSuccess(true);
        ajaxResult.setImg(imgUrl);
        return ajaxResult;
    }
}
