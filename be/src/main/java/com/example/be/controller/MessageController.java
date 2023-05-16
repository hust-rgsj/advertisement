package com.example.be.controller;

import com.example.be.entity.Message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/customer/messages")
public class MessageController {
    private List<Message> messages = new ArrayList<>();

    // 获取所有消息
    @GetMapping
    public List<Message> getAllMessages() {
        return messages;
    }

    // 根据ID获取消息
    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    // 创建新消息
    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        message.setId(messages.size() + 1);
        messages.add(message);
        return message;
    }

    // 更新消息
    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message updatedMessage) {
        for (Message message : messages) {
            if (message.getId() == id) {
                message.setTitle(updatedMessage.getTitle());
                message.setContent(updatedMessage.getContent());
                return message;
            }
        }
        return null;
    }

    // 删除消息
    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                messages.remove(message);
                return "Message deleted successfully.";
            }
        }
        return "Message not found.";
    }
}

