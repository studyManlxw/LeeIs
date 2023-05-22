package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (ClerkLabel)表实体类
 *
 * @author makejava
 * @since 2023-03-28 15:48:12
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_clerk_label")
public class ClerkLabel  {
    //id@TableId
    private Integer id;

    //店员id
    private Integer clerkId;
    //标签详情
    private String label;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //删除标记（0未删，1已删）
    private Integer isDelete;



}

