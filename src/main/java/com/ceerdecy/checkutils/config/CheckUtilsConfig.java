package com.ceerdecy.checkutils.config;

import com.ceerdecy.checkutils.properties.CheckUtilsProperties;
import com.ceerdecy.checkutils.service.CheckBeansService;
import com.ceerdecy.checkutils.service.CheckMapService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CeerDecy
 * @date 2023/2/27 21:11
 */
@Configuration
@EnableConfigurationProperties(CheckUtilsProperties.class)
public class CheckUtilsConfig {
    private final CheckUtilsProperties prop;
    public CheckUtilsConfig(CheckUtilsProperties prop) {
        this.prop = prop;
    }
    @Bean
    @ConditionalOnMissingBean
    public CheckBeansService checkBeansService(){
        return new CheckBeansService();
    }
    @Bean
    @ConditionalOnMissingBean
    public CheckMapService checkMapService(){
        return new CheckMapService();
    }

}
