package com.dhl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhl.model.entity.Clerk;
import com.dhl.model.vo.ClerkVo;
import com.dhl.model.vo.ShowClerkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (SysClerk)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-28 14:42:47
 */
@Mapper
public interface ClerkMapper extends BaseMapper<Clerk> {

    List<ShowClerkInfo> showClerkInfo();

    ClerkVo findClerkInfoById(@Param("id") Integer id);
}

