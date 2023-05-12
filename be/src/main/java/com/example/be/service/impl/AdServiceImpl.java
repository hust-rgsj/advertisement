package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.example.be.mapper.AdMapper;
import com.example.be.service.IAdService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-04-17
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

    @Override
    public List<Addto> ToDto(List<Ad> listAll) {
        List<Addto> adList = new ArrayList<>();
        for (Ad ad : listAll){
            Addto addto = new Addto();
            addto.setId(ad.getId());
            addto.setTitle(ad.getTitle());
            addto.setDescription(ad.getDescription());
            addto.setStatus(ad.getStatus());
            addto.setUrl(ad.getUrl());
            addto.setStartTime(ad.getStartTime());
            addto.setEndTime(ad.getEndTime());
            adList.add(addto);
        }
        return adList;
    }

    @Override
    public PageInfo<Addto> copyPageInfo(List<Addto> adList, PageInfo<Addto> pageInfodto,PageInfo<Ad> pageInfo) {
        pageInfodto.setList(adList);
        pageInfodto.setTotal(pageInfo.getTotal());
        pageInfodto.setPageNum(pageInfo.getPageNum());
        pageInfodto.setPageSize(pageInfo.getPageSize());
        pageInfodto.setSize(pageInfo.getSize());
        pageInfodto.setStartRow(pageInfo.getStartRow());
        pageInfodto.setEndRow(pageInfo.getEndRow());
        pageInfodto.setPages(pageInfo.getPages());
        pageInfodto.setPrePage(pageInfo.getPrePage());
        pageInfodto.setNextPage(pageInfo.getNextPage());
        pageInfodto.setIsFirstPage(pageInfo.isIsFirstPage());
        pageInfodto.setIsLastPage(pageInfo.isIsLastPage());
        pageInfodto.setHasPreviousPage(pageInfo.isHasPreviousPage());
        pageInfodto.setHasNextPage(pageInfo.isHasNextPage());
        pageInfodto.setNavigatePages(pageInfo.getNavigatePages());
        pageInfodto.setNavigatepageNums(pageInfo.getNavigatepageNums());
        pageInfodto.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        pageInfodto.setNavigateLastPage(pageInfo.getNavigateLastPage());

        return pageInfodto;
    }

    @Override
    public void updateStatusByCustomerId(Integer customerId) {
        LocalDateTime time = LocalDateTime.now();
        LambdaQueryWrapper<Ad> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Ad::getCustomerId,customerId);
        queryWrapper1.eq(Ad::getStatus, Status.ON).ge(Ad::getEndTime, time);
        List<Ad> list1 = list(queryWrapper1);
        for (Ad ad : list1) {
            ad.setStatus(Status.OFF);
            updateById(ad);
        }
        LambdaQueryWrapper<Ad> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Ad::getCustomerId,customerId);
        queryWrapper2.eq(Ad::getStatus, Status.PAID).le(Ad::getEndTime, time).ge(Ad::getStartTime, time);
        List<Ad> list2 = list(queryWrapper2);
        for (Ad ad : list2) {
            ad.setStatus(Status.ON);
            updateById(ad);
        }
    }


    @Override
    public List<Ad> getAdByCustomerId(Integer customerId) {
        updateStatusByCustomerId(customerId);
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getCustomerId, customerId);
        List<Ad> adList = list(queryWrapper);
        return adList;
    }

    public void updateStatus(){
        LocalDateTime time = LocalDateTime.now();
        LambdaQueryWrapper<Ad> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Ad::getStatus, Status.ON).ge(Ad::getEndTime, time);
        List<Ad> list1 = list(queryWrapper1);
        for (Ad ad : list1) {
            ad.setStatus(Status.OFF);
            updateById(ad);
        }
        LambdaQueryWrapper<Ad> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Ad::getStatus, Status.PAID).le(Ad::getEndTime, time).ge(Ad::getStartTime, time);
        List<Ad> list2 = list(queryWrapper2);
        for (Ad ad : list2) {
            ad.setStatus(Status.ON);
            updateById(ad);
        }
    }
}
