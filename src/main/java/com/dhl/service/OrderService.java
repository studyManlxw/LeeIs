package com.dhl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhl.model.entity.Order;

import java.util.List;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 15:07:15
 */
public interface OrderService extends IService<Order> {

    boolean saveOrder(Order order);

    List<Order> queryByStatus(Integer status);
}

