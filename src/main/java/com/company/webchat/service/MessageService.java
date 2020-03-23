package com.company.webchat.service;

import com.company.webchat.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    void save(Message message);
}
