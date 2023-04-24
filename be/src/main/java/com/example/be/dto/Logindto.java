package com.example.be.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logindto {

    private String token;

    private Integer id;

    private Integer type;

    private Long expire;
}
