package com.example.be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.example.be.mapper.AdMapper;
import com.example.be.service.IAdService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

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
}
