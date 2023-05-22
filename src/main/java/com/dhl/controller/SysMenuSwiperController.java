package com.dhl.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhl.common.result.Result;
import com.dhl.model.entity.MenuSwiper;
import com.dhl.model.vo.MenuSwiperVo;
import com.dhl.service.MenuSwiperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (SysMenuSwiper)表控制层
 *
 * @author makejava
 * @since 2023-04-04 09:59:33
 */
@Api(tags = "LeeIs菜单轮播图接口")
@CrossOrigin("*")
@RestController
@RequestMapping("swiper")
public class SysMenuSwiperController {
    /**
     * 服务对象
     */
    @Resource
    private MenuSwiperService sysMenuSwiperService;

    /**
     * 获取所有轮播图
     * @return
     */
    @ApiOperation("获取所有轮播图")
    @GetMapping("/find")
    public Result findSwiper(){
        List<MenuSwiperVo> menuSwiper = sysMenuSwiperService.findMenuSwiper();
        return Result.ok(menuSwiper);
    }
}

