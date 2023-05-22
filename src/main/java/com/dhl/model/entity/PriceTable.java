package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (PriceTable)表实体类
 *
 * @author makejava
 * @since 2023-04-13 16:43:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_price_table")
public class PriceTable  {
    //id@TableId
    private Integer id;
    //分类名称
    private String name;
    //上级菜单id
    private Integer parentMenuId;
    private Integer  price;
    //值类型对应所属类型id
    private Integer classId;




}

