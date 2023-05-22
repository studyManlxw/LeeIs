package com.dhl.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dhl.model.entity.PriceTable;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class TableInfoVo {
    //id@TableId
    private Integer id;
    //分类名称
    private String name;
    //最终封装的集合数据
    private Map<String, Map<String,Integer>> tableTreeMap;

}
