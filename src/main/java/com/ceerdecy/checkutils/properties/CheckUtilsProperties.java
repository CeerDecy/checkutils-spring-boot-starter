package com.ceerdecy.checkutils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author CeerDecy
 * @date 2023/2/27 21:06
 */
@Data
@ConfigurationProperties(prefix = "check-utils")
public class CheckUtilsProperties {
}
