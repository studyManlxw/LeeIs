package com.dhl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.mapper.ClerkLabelMapper;
import com.dhl.model.entity.ClerkLabel;
import com.dhl.service.ClerkLabelService;
import org.springframework.stereotype.Service;

/**
 * (ClerkLabel)表服务实现类
 *
 * @author makejava
 * @since 2023-03-28 15:20:11
 */
@Service("clerkLabelService")
public class ClerkLabelServiceImpl extends ServiceImpl<ClerkLabelMapper, ClerkLabel> implements ClerkLabelService {

}

