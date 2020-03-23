package com.company.webchat.service;

import com.company.webchat.dao.MessageDao;
import com.company.webchat.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    @Transactional
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    @Transactional
    public void save(Message message) {
         messageDao.save(message);
    }
}
