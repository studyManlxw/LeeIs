package com.dhl.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dhl.common.result.Result;
import com.dhl.model.entity.User;
import com.dhl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;

import me.chanjar.weixin.mp.api.WxMpService;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Api(tags = "微信接口")
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WxController {
    @Resource
    private WxMpService wxMpService;
    @Resource
    private UserService userService;
    @Value("${wx.mp.appid}")
    private String appId;
    @Value("${wx.mp.secret}")
    private String secret;
    @Value("${wx.mp.userInfoUrl}")
    private String userInfoUrl;


    @ApiOperation("验证微信服务器有效性")
    @GetMapping
    @ResponseBody
    public String wxInterface(String signature, String timestamp, String nonce, String echostr) {
        System.out.println("signature = " + signature + ", timestamp = " + timestamp + ", nonce = " + nonce + ", echostr = " + echostr);
        String token = "libeij";
        //1）将token、timestamp、nonce三个参数进行字典序排序
        List<String> list = Arrays.asList(token, timestamp, nonce);
        //排序
        Collections.sort(list);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        //加密
        try {
            MessageDigest instance = MessageDigest.getInstance("sha1");
            //s使用sha1进行加密，获得byte数组
            byte[] digest = instance.digest(stringBuilder.toString().getBytes());
            StringBuilder sum = new StringBuilder();
            for (byte b : digest) {
                sum.append(Integer.toHexString((b >> 4) & 15));
                sum.append(Integer.toHexString(b & 15));
            }
//            System.out.println("signature = " + signature);
            System.out.println("sum = " + sum);
            //3）开发者获得加密后的字符串可与 signature 对比，标识该请求来源于微信
            if (signature.equals(sum.toString())) {
                System.out.println("return的echostr");
                return echostr;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("return的null");
        return null;
    }

    @ApiOperation("微信用户授权")
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl, HttpServletRequest request) throws UnsupportedEncodingException {
        String redirectUrl = wxMpService.getOAuth2Service().buildAuthorizationUrl(
                userInfoUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl.replace("libeij", "#"), "utf-8")
        );
        log.info("【微信网页授权】redirectUrl={}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) throws Exception {
        log.info("【微信网页授权】code={}", code);
        log.info("【微信网页授权】state={}", returnUrl);
        WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(appId, secret, code);
        log.info("accessToken:" + accessToken);
        String openId = accessToken.getOpenId();
        log.info("【微信网页授权】openId={}", openId);
        WxOAuth2UserInfo wxMpUser = wxMpService.getOAuth2Service().getUserInfo(accessToken, null);
        //判断拿到的数据是否为空并且数据表中是否已经存在openId，不存在则加入数据库
        if (wxMpUser != null && userService.count(new LambdaQueryWrapper<User>().eq(User::getOpenId, openId)) == 0) {
            User wxUser = new User();
            wxUser.setName(wxMpUser.getNickname());
            wxUser.setHeadUrl(wxMpUser.getHeadImgUrl());
            wxUser.setOpenId(wxMpUser.getOpenid());
            wxUser.setSex(wxUser.getSex());
            userService.save(wxUser);
        }
        log.info("【微信网页授权】wxMpUser={}", JSON.toJSONString(wxMpUser));
        return "redirect:" + returnUrl + "?openId=" + openId;
    }


    /**
     * 封装得到的用户消息至map
     *
     * @param request
     * @return
     * @throws IOException
     * */


/*
 @PostMapping("/")
    public String receiveMessage(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        String message = "";
        try {
            //读取输入流，获取document对象
            Document document = reader.read(inputStream);
            //获得根节点root
            Element root = document.getRootElement();
            //获取所有子节点
            List<Element> elements = root.elements();
            for (Element element : elements) {
                //封装key，value至map
                map.put(element.getName(), element.getStringValue());
            }
            System.out.println("map = " + map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //如果是点击按钮事件
        if (map.get("MsgType").equals("event")){
            message = handleEvent(map);
        } else {
            //回复消息
            message = getReplyMessage(map);
        }
        return message;
    }
*/


/**
 * 处理事件推送
 * @param map
 * @return
 * */


/*private String handleEvent(Map<String, String> map) {
        String event = map.get("Event");
        switch (event){
            case "CLICK":
                if (map.get("EventKey").equals("1")){
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(map.get("FromUserName"));
                    textMessage.setFromUserName(map.get("ToUserName"));
                    textMessage.setMsgType("text");
                    textMessage.setContent("你点击的eventKey是1的按钮");
                    textMessage.setCreateTime(System.currentTimeMillis() / 1000);
                    //把textMessage转换为xml
                    XStream xStream = new XStream();
                    xStream.processAnnotations(TextMessage.class);
                    String xml = xStream.toXML(textMessage);
                    return xml;
                }
                break;
            case "VIEW":
                System.out.println("view");
                break;
            default:
                break;
        }
        return null;
    }*/


/**
 * 获得回复的消息内容
 *  给用户返回消息
 * @param map 接收到的xml消息转为map的形式传递过来
 * @return
 * */


//private String getReplyMessage(Map<String, String> map) {
//        TextMessage textMessage = new TextMessage();
//        textMessage.setToUserName(map.get("FromUserName"));
//        textMessage.setFromUserName(map.get("ToUserName"));
//        textMessage.setMsgType("text");
//        textMessage.setContent("欢迎关注本公众号！");
//        textMessage.setCreateTime(System.currentTimeMillis() / 1000);
//        //把textMessage转换为xml
//        XStream xStream = new XStream();
//        xStream.processAnnotations(TextMessage.class);
//        String xml = xStream.toXML(textMessage);
//        System.out.println("xml = " + xml);
//        return xml;
//    }


    /**
     * 接收XML
     *
     * @param request
     * @return
     * @throws IOException
     */


    @PostMapping("/")
    public String receiveMessage(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(b)) != -1) {
            System.out.println(new String(b, 0, len));
        }
        System.out.println("结束到用户消息");
        return "";
    }

}
