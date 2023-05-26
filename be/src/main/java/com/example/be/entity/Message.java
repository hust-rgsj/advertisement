package com.example.be.entity;

import com.baomidou.mybatisplus.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_customer")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Long id;
    private String title;
    private String content;

    // 带参构造函数
    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter和Setter方法

    public Long getId() {
        return id;
    }

    public void setId(int i) {
        this.id = (long) i;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public void setRecipientId(int userId) {
    }


}

