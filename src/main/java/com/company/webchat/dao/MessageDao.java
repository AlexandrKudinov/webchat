package com.company.webchat.dao;

import com.company.webchat.entity.*;

import java.util.List;

public interface MessageDao {
    List<Message> findAll();

    void save(Message message);
}
