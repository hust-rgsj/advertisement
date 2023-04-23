package com.example.be.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_ad")
@AllArgsConstructor
@NoArgsConstructor
public class TbAd implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    private Integer status;

    private String title;

    private String type;

    private String description;

    private String url;

    private BigDecimal price;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
