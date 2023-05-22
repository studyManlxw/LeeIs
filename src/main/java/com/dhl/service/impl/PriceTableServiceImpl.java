package com.dhl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.mapper.PriceTableMapper;
import com.dhl.model.entity.PriceTable;
import com.dhl.model.vo.PackagingTableToBoxVo;
import com.dhl.model.vo.TableInfoVo;
import com.dhl.service.PriceTableService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * (PriceTable)表服务实现类
 *
 * @author makejava
 * @since 2023-04-13 16:43:34
 */
@Slf4j
@Service("priceTableService")
public class PriceTableServiceImpl extends ServiceImpl<PriceTableMapper, PriceTable> implements PriceTableService {

    @Override
    public Map<String, Map<String,Integer>> getTableInfo() {
        LambdaQueryWrapper<PriceTable> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PriceTable::getClassId,0);
        List<PriceTable> rootTable = baseMapper.selectList(wrapper);
        return tableTree(rootTable);
    }


    /**
     * 循环获取表数据，并封装进入map集合
     */
    public  Map<String, Map<String,Integer>> tableTree(List<PriceTable> rootTable){
        Map<String, Map<String,Integer>> finaTimeClassAndPriceMp = new HashMap<>();
        for (PriceTable className : rootTable) {
            LambdaQueryWrapper<PriceTable> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(PriceTable::getClassId,className.getId()).orderByDesc(PriceTable::getPrice);
            List<PriceTable> timeAndPriceList = baseMapper.selectList(wrapper);
            log.info("timeAndPriceList:"+timeAndPriceList);
            Map<String, Integer> timeClassAndPriceMap = timeAndPriceList
                    .stream()
                    .collect(Collectors.toMap(PriceTable::getName, PriceTable::getPrice));
            log.info("timeClassAndPriceMap"+timeClassAndPriceMap);
            finaTimeClassAndPriceMp.put(className.getName(),timeClassAndPriceMap);
        }
        return finaTimeClassAndPriceMp;
    }

}

