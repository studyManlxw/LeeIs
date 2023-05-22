package com.dhl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.mapper.OrderMapper;
import com.dhl.model.entity.Order;
import com.dhl.service.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 15:07:15成
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    /**
     * 将前端传递的订单信息保存至订单表
     * @param order
     * @return
     */
    @Override
    public boolean saveOrder(Order order) {
        if (baseMapper.insert(order) > 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据订单状态查询订单信息
     * @param status
     * @return
     */
    @Override
    public List<Order> queryByStatus(Integer status) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getStatus,status);
        if (status == 0){
            //如果状态为0，则查询全部订单信息
            return baseMapper.selectList(null);
        }
        return baseMapper.selectList(wrapper);
    }
}

