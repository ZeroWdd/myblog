package com.wdd.myblog.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname FilePath
 * @Description None
 * @Date 2019/7/8 8:40
 * @Created by WDD
 */
@Component
@ConfigurationProperties(prefix="var")
public class FilePath {
    private String filePath;
    private String contextPath = System.getProperty("user.dir");  // E:\Code\Java_text\idea-workspace\myblog

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
