package com.example.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addto {

    private Integer id;
    private String title;
    private String description;
    private Integer status;
    private String url;
    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
