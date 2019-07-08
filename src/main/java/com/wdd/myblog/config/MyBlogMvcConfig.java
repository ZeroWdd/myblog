package com.wdd.myblog.config;

import com.wdd.myblog.util.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MyBlogMvcConfig
 * @Description None
 * @Date 2019/7/7 10:52
 * @Created by WDD
 */
@Component
public class MyBlogMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FilePath filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + filePath.getContextPath() + filePath.getFilePath());
    }

}
