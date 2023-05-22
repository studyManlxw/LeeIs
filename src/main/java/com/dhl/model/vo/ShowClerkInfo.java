package com.dhl.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dhl.model.entity.ClerkLabel;
import lombok.Data;

import java.util.List;

@Data
public class ShowClerkInfo {
    //店员表id@TableId
    private Integer id;
    //店员名称
    private String name;
    private Integer sex;
    //店员位置
    private String position;
    //店员等级
    private String level;
    //在线状态（0表示不在线，1表示在线）
    private Integer status;
    //标签id
    @TableField(exist = false)
    private List<String> labelList;
    //头像路径
    private String avatar;
    //语音条路径
    private String voice;
    //文案
    private String motto;
}
