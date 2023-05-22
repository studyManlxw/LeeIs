package com.dhl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.mapper.MenuSwiperMapper;
import com.dhl.model.entity.MenuSwiper;
import com.dhl.model.vo.MenuSwiperVo;
import com.dhl.service.MenuSwiperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (MenuSwiper)表服务实现类
 *
 * @author makejava
 * @since 2023-04-04 09:58:07
 */
@Slf4j
@Service("menuSwiperService")
public class MenuSwiperServiceImpl extends ServiceImpl<MenuSwiperMapper, MenuSwiper> implements MenuSwiperService {


    /**
     * 获取所有菜单轮播图
     * @return
     */
    @Override
    public List<MenuSwiperVo> findMenuSwiper() {
        List<MenuSwiperVo> menuSwiperVoList = new ArrayList<>();
        LambdaQueryWrapper<MenuSwiper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MenuSwiper::getIsDelete,0);
        List<MenuSwiper> menuSwipers = baseMapper.selectList(wrapper);
//        log.info("menSwipers:" + menuSwipers);
        for (MenuSwiper menuSwiper : menuSwipers) {
            MenuSwiperVo menuSwiperVo = new MenuSwiperVo();
            BeanUtils.copyProperties(menuSwiper,menuSwiperVo);
            menuSwiperVoList.add(menuSwiperVo);
        }

        return menuSwiperVoList;
    }
}

