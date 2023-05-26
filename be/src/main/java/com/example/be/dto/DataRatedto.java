package com.example.be.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRatedto {
    private  Integer display;

    private Integer click;

    private String rate;
}
