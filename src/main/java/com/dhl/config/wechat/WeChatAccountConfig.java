package com.dhl.config.wechat;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx.mp")//使用@ConfigurationProperties转换wechat为bean,创建类
public class WeChatAccountConfig {
    private String appId;
    private String secret;
}
