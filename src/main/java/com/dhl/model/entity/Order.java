package com.dhl.model.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2023-04-17 15:07:26
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_order")
public class Order  {
    //订单id@TableId
    private Integer id;
    //接单店员名称
    private String name;
    //订单所需店员性别
    private Integer orderSex;
    //订单类型
    private String type;
    //订单时常
    private String time;
    //订单金额
    private Integer price;
    //下单微信
    private String wxNumber;
    //订单数量
    private Integer count;
    //订单总金额
    private Integer totalMoney;
    //订单备注
    private String remark;
    //订单类型（0表示随机单，1表示指定单）
    private Integer orderType;
    //订单状态 1表示待完成，2表示进行中，3表示已完成
    private Integer status;
    //订单创建时间
    private Date createTime;
    //订单修改时间
    private Date updateTime;



}

