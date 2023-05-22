package com.dhl.controller;

import com.dhl.common.result.Result;
import com.dhl.model.entity.Order;
import com.dhl.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2023-04-17 15:08:05
 */
@Api(tags = "订单接口")
@RestController
@RequestMapping("order")
public class OrderController  {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;



    /**
     * 接收前端订单，保存并使订单生效
     * @param order 前端传递订单数据
     * @return
     */
    //TODO:目前为接收随机单，后续需要完成订单生效发布
    @ApiOperation("接收前端提交随机单")
    @PostMapping("/randomOrder")
    public Result getSubmitData(@RequestBody Order order){
        System.out.println("order = " + order);
        //设置订单为随机单
        order.setOrderType(0);
        boolean success = orderService.saveOrder(order);
        return Result.ok(success);
    }


    @ApiOperation("接收前端提交指定单")
    @PostMapping("/assignOrder")
    public Result getAssignOrder(@RequestBody Order order){
        System.out.println("order = " + order);
        //设置订单为指定单
        order.setOrderType(1);
        boolean success = orderService.saveOrder(order);
        return Result.ok(success);
    }


    /**
     * 在个人中心根据status查询订单状态
     * @param status 0为查询全部，1为查询待完成，2查询进行中，3查询已完成
     * @return 查询结果集合
     */
    @ApiOperation("根据订单状态查询订单")
    @GetMapping("/queryByStatus/{status}")
    public Result queryOrderStatus(@PathVariable Integer status){
        List<Order> resultList = orderService.queryByStatus(status);
        return Result.ok(resultList);
    }

}

