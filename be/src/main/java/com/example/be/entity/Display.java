package com.example.be.entity;

import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("tb_display")
@AllArgsConstructor
@NoArgsConstructor
public class Display implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer adId;

    private Integer clickCount;

    private Integer displayCount;

    private String conversionRate;


}
