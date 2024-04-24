package com.wedu.modules.miniprogram.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "miniprogram")
public class MiniProgramProperties {

    private String appId;

    private String appSecret;
}
