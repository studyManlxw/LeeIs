package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-04-24 15:12:10
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User  {
    //id@TableId
    private Long id;

    //用户名
    private String username;
    //密码
    private String password;
    //姓名
    private String name;
    //用户余额
    private Integer balance;
    //手机
    private String phone;
    //头像地址
    private String headUrl;
    //性别
    private Integer sex;
    //微信openId
    private String openId;
    //描述
    private String description;
    //是否为店员（1：是 0：不是）
    private Integer isClerk;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //删除标记（0:不可用 1:可用）
    private Integer isDeleted;



}

