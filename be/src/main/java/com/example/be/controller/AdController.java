package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.example.be.service.IAdService;
import com.example.be.utils.AliOSSUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-17
 */
@RestController
@RequestMapping({"/customer/advertisement","/admin/advertisement"})
public class AdController {

    @Autowired
    private IAdService adService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/add")
    public R<String> add(@RequestBody Ad ad){
        Long customerId = BaseContext.getCurrentId();
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getTitle,ad.getTitle());
        List<Ad> list = adService.list(queryWrapper);
        if(!list.isEmpty()){
            return R.error("该广告已存在");
        }
        ad.setCustomerId(Math.toIntExact(customerId));
        ad.setCreateTime(LocalDateTime.now());
        ad.setStatus(Status.EXAMING);
        adService.save(ad);

        return R.success("广告信息添加成功");
    }



    @PostMapping("/update")
    public R<Ad> update(@RequestBody Ad ad){

        ad.setUpdateTime(LocalDateTime.now());
        if(ad.getStatus() == Status.NOT_PASS){
            ad.setStatus(Status.EXAMING);
        }
        adService.updateById(ad);
        return R.success(ad);
    }

    @PostMapping("/examine")
    public R<String> examine(@RequestBody Ad ad){
        Ad advertisement = adService.getById(ad);
        Integer status = ad.getStatus();
        if(status == Status.PASS){
            advertisement.setStatus(status);
            adService.updateById(advertisement);
            return R.success("审核通过");
        }
        if(status == Status.NOT_PASS){
            ad.setStatus(status);
            adService.updateById(advertisement);
            return R.success("审核不通过，原因为："+ad.getReason()+",请修改");
        }
        return null;
    }

    @PostMapping("/set")
    public void set(@RequestBody Ad ad){

        adService.updateById(ad);

    }

    @GetMapping("/stop")
    public R<String> stop(@RequestParam  Integer adId){
        Ad ad = adService.getById(adId);
        ad.setStatus(Status.STOP);
        adService.updateById(ad);
        return R.success("广告服务已终止");
    }

    @PostMapping("/upload")
    public R<String> upload(MultipartFile image) throws Exception {
        String url = aliOSSUtils.upload(image);
        return R.success(url);
    }

    @GetMapping("/page")
    public PageInfo<Addto> page(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        Integer customerId = Math.toIntExact(BaseContext.getCurrentId());
        adService.updateStatusByCustomerId(customerId);
        PageHelper.startPage(pageNum, 6);

        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getCustomerId,customerId);
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), Ad::getTitle,msg);
        queryWrapper.orderByDesc(Ad::getUpdateTime);
        List<Ad> listAll = adService.list(queryWrapper);
        PageInfo<Ad> pageInfo = new PageInfo<>(listAll);
        List<Addto> adList = adService.ToDto(listAll);
        PageInfo<Addto> pageInfodto = new PageInfo<>();

        return adService.copyPageInfo(adList,pageInfodto,pageInfo);
    }


    //返回广告全部信息
    @GetMapping("/adId/{adId}")
    public Ad getByAdId(@PathVariable Integer adId){
        Ad ad = adService.getById(adId);
        adService.updateStatusByCustomerId(ad.getCustomerId());
        return ad;
    }

    //返回广告详情页
    @GetMapping("/adDetail/{adId}")
    public R<Addto> AdDetail(@PathVariable Integer adId){
        Ad ad = adService.getById(adId);
        Addto addto = adService.getDetail(ad);
        return R.success(addto);
    }


}
