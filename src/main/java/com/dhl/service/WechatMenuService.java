package com.dhl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhl.model.entity.WechatMenu;
import com.dhl.model.vo.MenuVo;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * (WechatMenu)表服务接口
 *
 * @author makejava
 * @since 2023-04-27 20:54:52
 */
public interface WechatMenuService extends IService<WechatMenu> {

    List<MenuVo> findMenuInfo();

    List<WxMenuButton> syncMenu();
}

