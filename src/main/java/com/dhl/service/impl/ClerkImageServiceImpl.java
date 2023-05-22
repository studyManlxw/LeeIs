package com.dhl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.dhl.mapper.ClerkImageMapper;
import com.dhl.model.entity.ClerkImage;
import com.dhl.service.ClerkImageService;
import org.springframework.stereotype.Service;

/**
 * (ClerkImage)表服务实现类
 *
 * @author makejava
 * @since 2023-03-28 15:20:21
 */
@Service("clerkImageService")
public class ClerkImageServiceImpl extends ServiceImpl<ClerkImageMapper, ClerkImage> implements ClerkImageService {

}

