package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Clerk)表实体类
 *
 * @author makejava
 * @since 2023-03-28 15:23:06
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_clerk")
public class Clerk  {
    //店员表id@TableId
    private Integer id;

    //店员名称
    private String name;
    //微信openid
    private String openId;
    //店员年纪
    private Integer age;
    //店员性别(0表示女，1表示男)
    private Integer sex;
    //店员位置
    private String position;
    //文案
    private String motto;
    //店员等级
    private String level;
    //在线状态（0表示不在线，1表示在线）
    private Integer status;
    //标签id
    private Integer labelId;
    @TableField(exist = false)
    private String imagePath;
    //头像路径
    private String avatar;
    //语音条路径
    private String voice;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //是否删除（0表示未删除，1表示删除）
    private Integer isDelete;



}

