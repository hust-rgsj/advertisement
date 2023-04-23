package com.example.be.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
@AllArgsConstructor
@NoArgsConstructor
public class TbOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderNumber;

    private Integer userId;

    private Integer id;

    private LocalDateTime payTime;

    private Integer adId;

    private BigDecimal amount;


}
