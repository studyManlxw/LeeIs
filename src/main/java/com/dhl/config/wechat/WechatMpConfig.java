package com.dhl.config.wechat;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 微信公众号MPSDK配置
 * 主要用来将值set到 WxMpService，而WxMpService就是weixin.mp.api提供的
 */
@Configuration
public class WechatMpConfig {
    @Resource
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(weChatAccountConfig.getAppId());
        wxMpDefaultConfig.setSecret(weChatAccountConfig.getSecret());
        return wxMpDefaultConfig;
    }

}
