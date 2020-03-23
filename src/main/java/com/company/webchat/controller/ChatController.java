package com.company.webchat.controller;


import com.company.webchat.entity.Message;
import com.company.webchat.entity.User;
import com.company.webchat.service.MessageService;
import com.company.webchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping("/chat")
    public String showChat() {
        return "chat";
    }


    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        user.addMessage(chatMessage);
        messageService.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public List<Message> getAllMessages() {
        return   messageService.findAll();
    }


}
