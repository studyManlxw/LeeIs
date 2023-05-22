package com.dhl.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClerkVo {
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
    @TableField(exist = false)
    private List<String> labelList;
    @TableField(exist = false)
    private List<String> imageList;
    //头像路径
    private String avatar;
    //语音条路径
    private String voice;
}
