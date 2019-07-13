package com.wdd.myblog.service;

import java.util.Map;

/**
 * @Classname BlogConfigService
 * @Description None
 * @Date 2019/7/10 19:34
 * @Created by WDD
 */
public interface BlogConfigService {
    Map<String,String> getAllConfigs();

    int updateConfig(String configName, String configValue);
}
