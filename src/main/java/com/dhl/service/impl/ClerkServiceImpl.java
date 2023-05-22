package com.dhl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhl.mapper.ClerkMapper;
import com.dhl.model.entity.Clerk;
import com.dhl.model.entity.ClerkImage;
import com.dhl.model.entity.ClerkLabel;
import com.dhl.model.vo.ClerkVo;
import com.dhl.model.vo.ShowClerkInfo;
import com.dhl.service.ClerkImageService;
import com.dhl.service.ClerkLabelService;
import com.dhl.service.ClerkService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (SysClerk)表服务实现类
 *
 * @author makejava
 * @since 2023-03-28 14:42:47
 */
@Service("clerkService")
public class ClerkServiceImpl extends ServiceImpl<ClerkMapper, Clerk> implements ClerkService {
    /**
     * 获取首页所需的所有店员信息
     *
     * @return
     */
    @Resource
    private ClerkLabelService clerkLabelService;
    @Resource
    private ClerkImageService clerkImageService;

    @Override
    public List<ShowClerkInfo> showClerkInfo() {
        List<ShowClerkInfo> allClerkInfos = new ArrayList<>();
        //根据id查询label表中的对应数据
        List<Clerk> clerks = baseMapper.selectList(null);
        for (Clerk clerk : clerks) {
            LambdaQueryWrapper<ClerkLabel> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ClerkLabel::getClerkId, clerk.getLabelId());
            List<ClerkLabel> labels = clerkLabelService.list(wrapper);
            List<String> list = labels.stream().map(ClerkLabel::getLabel).collect(Collectors.toList());
//            System.out.println("list = " + list);
            ShowClerkInfo clerkInfo = new ShowClerkInfo();
            BeanUtils.copyProperties(clerk, clerkInfo);
            clerkInfo.setLabelList(list);
//            System.out.println("clerkInfo = " + clerkInfo);
            allClerkInfos.add(clerkInfo);
        }
//        List<ShowClerkInfo> allClerkInfos = baseMapper.showClerkInfo();
        return allClerkInfos;
    }

    @Override
    public ClerkVo findClerkInfoById(Integer id) {
//        ClerkVo clerk = baseMapper.findClerkInfoById(id);
        ClerkVo clerkInfoVo = new ClerkVo();
        //根据id查询label表中的对应数据
        LambdaQueryWrapper<Clerk> clerkWrapper = new LambdaQueryWrapper<>();
        clerkWrapper.eq(Clerk::getId, id);
        Clerk clerkInfo = baseMapper.selectOne(clerkWrapper);
        LambdaQueryWrapper<ClerkImage> imageWrapper = new LambdaQueryWrapper<>();
        imageWrapper.eq(ClerkImage::getClerkId, clerkInfo.getId());
        List<ClerkImage> imageList = clerkImageService.list(imageWrapper);
        List<String> imagePathList = imageList.stream().map(ClerkImage::getImagePath).collect(Collectors.toList());
        LambdaQueryWrapper<ClerkLabel> clerkLabelWrapper = new LambdaQueryWrapper<>();
        clerkLabelWrapper.eq(ClerkLabel::getClerkId, id);
        List<String> LabelList = clerkLabelService.list(clerkLabelWrapper).stream().map(ClerkLabel::getLabel).collect(Collectors.toList());
        BeanUtils.copyProperties(clerkInfo, clerkInfoVo);
        clerkInfoVo.setImageList(imagePathList);
        clerkInfoVo.setLabelList(LabelList);
        return clerkInfoVo;
    }

    /**
     * 通过菜单选择栏value排序店员信息并返回
     *
     * @param value
     * @return
     */
    @Override
    public List<ShowClerkInfo> findByValue(Integer value) {
        LambdaQueryWrapper<Clerk> wrapper = new LambdaQueryWrapper<>();
        List<Clerk> clerks = baseMapper.selectList(null);
        List<ShowClerkInfo> clerkInfoList = null;
        switch (value) {
            case 2:
                clerkInfoList = showClerkInfo();
                break;
            case 1: //只看男生
                List<Clerk> clerksByValueBoy = clerks.stream().filter(clerk -> clerk.getSex() == 1).collect(Collectors.toList());
                clerkInfoList = clerksToClerksVoList(clerksByValueBoy);
                break;
            case 0://只看女生
                List<Clerk> clerksByValueGirl = clerks.stream().filter(clerk -> clerk.getSex() == 0).collect(Collectors.toList());
                clerkInfoList = clerksToClerksVoList(clerksByValueGirl);
                break;
            case 4://按等级排序
                wrapper.orderByAsc(Clerk::getLevel);
                clerkInfoList = clerksToClerksVoList(baseMapper.selectList(wrapper));
                break;
            case 3:
                //Todo 受欢迎程度度排序
                break;
            case 5:
                clerkInfoList = showClerkInfo();
                break;
        }
        return clerkInfoList;
    }


    public List<ShowClerkInfo> clerksToClerksVoList(List<Clerk> clerkList) {
        List<ShowClerkInfo> showClerkInfoList = new ArrayList<>();
        for (Clerk clerk : clerkList) {
            LambdaQueryWrapper<ClerkLabel> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ClerkLabel::getClerkId, clerk.getLabelId());
            List<ClerkLabel> labels = clerkLabelService.list(wrapper);
            List<String> list = labels.stream().map(ClerkLabel::getLabel).collect(Collectors.toList());
            ShowClerkInfo clerkInfo = new ShowClerkInfo();
            BeanUtils.copyProperties(clerk, clerkInfo);
            clerkInfo.setLabelList(list);
            showClerkInfoList.add(clerkInfo);
        }
        return showClerkInfoList;
    }

}

