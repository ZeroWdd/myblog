package com.wdd.myblog.util;

import java.io.File;

/**
 * @Classname UploadUtil
 * @Description None
 * @Date 2019/7/7 21:00
 * @Created by WDD
 */
public class UploadUtil {
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）

    public final static String IMG_BLOG_COVER = "upload/imgs/cover"; //博客封面图片路径

    public static File getImgBlogCover(){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/static/" + IMG_BLOG_COVER);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
