package com.dhl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhl.model.entity.MenuSwiper;
import com.dhl.model.vo.MenuSwiperVo;

import java.util.List;

/**
 * (MenuSwiper)表服务接口
 *
 * @author makejava
 * @since 2023-04-04 09:58:07
 */
public interface MenuSwiperService extends IService<MenuSwiper> {

    List<MenuSwiperVo> findMenuSwiper();
}

