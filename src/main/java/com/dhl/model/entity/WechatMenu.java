package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (WechatMenu)表实体类
 *
 * @author makejava
 * @since 2023-04-27 20:54:41
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wechat_menu")
public class WechatMenu  {
    //编号@TableId
    private Long id;

    //上级id
    private Long parentId;
    //菜单名称
    private String name;
    //类型
    private String type;
    //网页 链接，用户点击菜单可打开链接
    private String url;
    //菜单KEY值，用于消息接口推送
    private String meunKey;
    //排序
    private Integer sort;
    //创建时间
    private Date createTime;
    
    private Date updateTime;
    
    private Integer isDeleted;



}

