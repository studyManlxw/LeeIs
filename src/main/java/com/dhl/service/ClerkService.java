package com.dhl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhl.model.entity.Clerk;
import com.dhl.model.vo.ClerkVo;
import com.dhl.model.vo.ShowClerkInfo;

import java.util.List;

/**
 * (SysClerk)表服务接口
 *
 * @author makejava
 * @since 2023-03-28 14:42:47
 */

public interface ClerkService extends IService<Clerk> {
    List<ShowClerkInfo> showClerkInfo();

    ClerkVo findClerkInfoById(Integer id);

    List<ShowClerkInfo> findByValue(Integer value);
}

