package com.dhl.controller;


import cn.hutool.json.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.dhl.common.result.Result;
import com.dhl.model.entity.Clerk;
import com.dhl.model.vo.ClerkVo;
import com.dhl.model.vo.ShowClerkInfo;
import com.dhl.service.ClerkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysClerk)表控制层
 *
 * @author makejava
 * @since 2023-03-28 14:42:47
 */

@CrossOrigin("*")
@Api(tags = "LeeIs店员接口")
@RestController
@RequestMapping("/sysClerk")
public class SysClerkController  {
    /**
     * 服务对象
     */
    @Resource
    private ClerkService clerkService;


    /**
     * 获取所有店员的首页信息
     * @return
     */
    @ApiOperation("获取所有店员信息")
    @GetMapping("/show")
    public Result showClerkInfo(){
        List<ShowClerkInfo> clerks = clerkService.showClerkInfo();
        return Result.ok(clerks);
    }


    @ApiOperation("跟据id查询店员详情页信息")
    @GetMapping("/find/{id}")
    public Result findClerkInfoById(@PathVariable Integer id){
        System.out.println("id = " + id);
         ClerkVo clerk = clerkService.findClerkInfoById(id);
         return Result.ok(clerk);
    }

    @ApiOperation("通过菜单选择栏value获取")
    @GetMapping("/getByValue/{value}")
    public Result findByValue(@PathVariable Integer value){
        List<ShowClerkInfo> clerkInfos = clerkService.findByValue(value);
        return Result.ok(clerkInfos);
    }

}

