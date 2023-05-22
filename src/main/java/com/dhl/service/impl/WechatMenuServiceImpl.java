package com.dhl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.common.result.Result;
import com.dhl.mapper.WechatMenuMapper;
import com.dhl.model.entity.WechatMenu;
import com.dhl.model.vo.MenuVo;
import com.dhl.service.WechatMenuService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (WechatMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-04-27 20:54:52
 */
@Slf4j
@Service("wechatMenuService")
public class WechatMenuServiceImpl extends ServiceImpl<WechatMenuMapper, WechatMenu> implements WechatMenuService {
        @Value("${wx.mp.menuButtonUrl}")
        private String menuButtonUIrl;

    /**
     * 生成微信菜单数据
     */
    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> list = new ArrayList<>();
        List<WechatMenu> wechatMenus = baseMapper.selectList(null);
        //封装所有一级菜单
        List<WechatMenu> oneMenuList = wechatMenus.stream().filter(wechatMenu -> wechatMenu.getParentId().longValue() == 0).collect(Collectors.toList());
        for (WechatMenu oneMenu : oneMenuList) {
            //wechatMenu ---> MenuVO
            MenuVo oneMenuVo = new MenuVo();
            BeanUtils.copyProperties(oneMenu,oneMenuVo);
            //一级菜单包含的二级菜单
            List<WechatMenu> twoMenuList = wechatMenus.stream()
                    .filter(menu -> menu.getParentId().longValue() == oneMenu.getId())
                    .collect(Collectors.toList());
            List<MenuVo> children = new ArrayList<>();
            for (WechatMenu twoMenu : twoMenuList) {
                MenuVo  twoMenuVo = new MenuVo();
                BeanUtils.copyProperties(twoMenu,twoMenuVo);
                children.add(twoMenuVo);
            }
            oneMenuVo.setChildren(children);
            list.add(oneMenuVo);
        }
        return list;
    }

    /**
     * 同步微信公众号菜单
     */
    @Override
    public List<WxMenuButton> syncMenu()  {
        //所有菜单数据
        List<WechatMenu> wechatMenuList = baseMapper.selectList(null);
        //用于存放最终菜单数据
        List<WxMenuButton> wxMenuButtonList = new ArrayList<>();
        //过滤出一级菜单
        List<WechatMenu> oneMenuList = wechatMenuList.stream().filter(wechatMenu -> wechatMenu.getParentId() == 0).collect(Collectors.toList());
        //将一级菜单的所有url添加前端路径
        for (WechatMenu wechatMenu : oneMenuList) {
            String url = wechatMenu.getUrl();
            wechatMenu.setUrl(menuButtonUIrl + url);
        }
        for (WechatMenu oneMenu : oneMenuList) {
            WxMenuButton wxOneMenuButton = new WxMenuButton();
            BeanUtils.copyProperties(oneMenu,wxOneMenuButton);
            //获取与一级菜单id相同的子菜单
            List<WechatMenu> twoMenuList = wechatMenuList.stream().filter(wechatMenu -> wechatMenu.getParentId() == oneMenu.getId()).collect(Collectors.toList());
            for (WechatMenu wechatMenu : twoMenuList) {
                String url = wechatMenu.getUrl();
                wechatMenu.setUrl(menuButtonUIrl + url);
            }
            List<WxMenuButton> wxTwoMenuButtonList = new ArrayList<>();
            for (WechatMenu twoMenu : twoMenuList) {
                WxMenuButton wxTwoMenuButton = new WxMenuButton();
                BeanUtils.copyProperties(twoMenu,wxTwoMenuButton);
                wxTwoMenuButtonList.add(wxTwoMenuButton);
                wxOneMenuButton.setSubButtons(wxTwoMenuButtonList);
            }
            wxMenuButtonList.add(wxOneMenuButton);
        }

        return wxMenuButtonList;
    }
}

