package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDetaildto {
    private Integer id;
    private String title;
    private String description;
    private Integer status;
    private String url;
    private BigDecimal price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
