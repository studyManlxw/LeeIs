package com.dhl.controller;

import com.dhl.common.result.Result;
import com.dhl.model.entity.User;
import com.dhl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/leeIs/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("个人中心获取用户信息")
    @GetMapping("/userInfo/{openId}")
    public Result getUserInfoByOpenId(@PathVariable String openId){
        User userInfo = userService.getUserInfoByOpenId(openId);
        if (userInfo == null){
            return Result.fail();
        }
        return Result.ok(userInfo);
    }
}
