package com.example.be.entity;

import com.baomidou.mybatisplus.annotation.TableName;

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
@NoArgsConstructor

public class DisplayStatistics {
    private Integer clickCount; // 点击次数
    private Integer displayCount; // 展示次数
    private String conversionRate; // 转化率

    // 构造方法
    public DisplayStatistics(Integer clickCount, Integer displayCount, String conversionRate) {
        this.clickCount = clickCount;
        this.displayCount = displayCount;
        this.conversionRate = conversionRate;
    }

    // Getter 和 Setter 方法
    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(Integer displayCount) {
        this.displayCount = displayCount;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {
        this.conversionRate = conversionRate;
    }
}
