package com.example.be.dto;

import com.sun.xml.txw2.annotation.XmlNamespace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accountdto {
    private BigDecimal balance;

    private List<String> log;
}
