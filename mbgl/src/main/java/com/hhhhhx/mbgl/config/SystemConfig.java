package com.hhhhhx.mbgl.config;

import com.hhhhhx.mbgl.config.properties.WxConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system")
@Data
public class SystemConfig {
    private WxConfig wx;
}
