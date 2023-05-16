package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDetaildto {
    private String title;
    private String description;
    private Integer auditStatus;
    private Integer payStatus;
    private Integer arrangeStatus;
    private String  url;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
