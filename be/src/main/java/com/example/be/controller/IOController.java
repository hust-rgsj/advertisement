package com.example.be.controller;

import com.example.be.common.R;
import com.example.be.utils.AliOSSUtils;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class IOController {

    @Autowired
    private AliOSSUtils aliOSSUtils;


    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws Exception {
        String url = aliOSSUtils.upload(file);
        return R.success(url);
    }



}
