package com.dhl.controller;

import com.dhl.common.result.Result;
import com.dhl.model.vo.MenuVo;
import com.dhl.service.WechatMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (WechatMenu)表控制层
 *
 * @author makejava
 * @since 2023-04-27 20:54:52
 */
@Api(tags = "微信菜单接口")
@RestController
@RequestMapping("wechatMenu")
public class WechatMenuController {
    /**
     * 服务对象
     */
    @Value("${wx.mp.secret}")
    private String secret;
    @Resource
    private WechatMenuService wechatMenuService;
    @Resource
    private WxMpService wxMpService;


    @ApiOperation(value = "获取全部菜单")
    @GetMapping("findMenuInfo")
    public Result findMenuInfo() {
        List<MenuVo> list = wechatMenuService.findMenuInfo();
        return Result.ok(list);
    }


    @ApiOperation("同步微信公众号菜单栏")
    @GetMapping("/syncMenu")
    public Result syncMenu() throws WxErrorException {
        WxMenu wxMenu = new WxMenu();
        List<WxMenuButton> wxMenuButtons = wechatMenuService.syncMenu();
        wxMenu.setButtons(wxMenuButtons);
        String result = null;
        try {
            result = wxMpService.getMenuService().menuCreate(wxMenu);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        return Result.ok(wxMenuButtons);
    }
}

