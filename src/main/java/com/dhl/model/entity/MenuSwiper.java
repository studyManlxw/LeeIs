package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (MenuSwiper)表实体类
 *
 * @author makejava
 * @since 2023-04-04 09:57:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu_swiper")
public class MenuSwiper  {
    //id@TableId
    private Integer id;

    //图片路径
    private String imagePath;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //删除标记（0未删，1已删）
    private Integer isDelete;



}

