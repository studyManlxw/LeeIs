package com.dhl.controller;

import com.dhl.common.result.Result;
import com.dhl.service.PriceTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (SysPriceTable)表控制层
 *
 * @author makejava
 * @since 2023-04-13 16:43:48
 */
@Api(tags = "价格表接口")
@RestController
@RequestMapping("sysPriceTable")
public class SysPriceTableController {
    /**
     * 服务对象
     */
    @Resource
    private PriceTableService priceTableService;

    @ApiOperation("客户端获取价格表数据")
    @GetMapping("/getTableInfo")
    public Result getTableInfo(){
        Map<String, Map<String,Integer>> tableInfoVos = priceTableService.getTableInfo();
        return Result.ok(tableInfoVos);
    }
}

