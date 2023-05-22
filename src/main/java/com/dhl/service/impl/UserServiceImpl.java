package com.dhl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.mapper.UserMapper;
import com.dhl.model.entity.User;
import com.dhl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-04-24 15:12:44
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 返回用户数据至个人中心
     * @param openId 用户openId
     * @return
     */
    @Override
    public User getUserInfoByOpenId(String openId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId,openId);
        User user = baseMapper.selectOne(wrapper);
        System.out.println("user = " + user);
        if (user!=null){
            return user;
        }
        return null;
    }
}

