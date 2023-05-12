package com.example.be.service;

import com.example.be.common.R;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-17
 */
public interface IAdService extends IService<Ad> {
    List<Addto> ToDto(List<Ad> listAll);

    PageInfo<Addto> copyPageInfo(List<Addto> adList, PageInfo<Addto> pageInfodto, PageInfo<Ad> pageInfo);

    void updateStatus(Integer customerId);

    List<Ad> getAdByCustomerId(Integer customerId);
}
