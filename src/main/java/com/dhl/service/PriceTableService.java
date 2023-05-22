package com.dhl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhl.model.entity.PriceTable;
import com.dhl.model.vo.PackagingTableToBoxVo;
import com.dhl.model.vo.TableInfoVo;

import java.util.List;
import java.util.Map;

/**
 * (PriceTable)表服务接口
 *
 * @author makejava
 * @since 2023-04-13 16:43:34
 */
public interface PriceTableService extends IService<PriceTable> {

    Map<String, Map<String,Integer>> getTableInfo();

}

