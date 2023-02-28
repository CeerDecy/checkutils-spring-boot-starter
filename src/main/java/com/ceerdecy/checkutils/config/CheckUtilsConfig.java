package com.ceerdecy.checkutils.config;

import com.ceerdecy.checkutils.properties.CheckUtilsProperties;
import com.ceerdecy.checkutils.service.CheckAopService;
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
    public CheckAopService checkAopService(){
        return new CheckAopService();
    }

}
