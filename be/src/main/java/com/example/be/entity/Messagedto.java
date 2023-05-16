package com.example.be.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import javax.print.attribute.SetOfIntegerSyntax;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Messagedto {

    private Integer id;

    private String title;

    private String content;
}
