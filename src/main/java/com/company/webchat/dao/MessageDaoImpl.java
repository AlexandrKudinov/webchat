package com.company.webchat.dao;


import com.company.webchat.entity.Message;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Message> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Message> theQuery = currentSession.createQuery("from Message", Message.class);

        List<Message> messages;
        try {
            messages = theQuery.getResultList();
        } catch (Exception e) {
            messages = null;
        }
        return messages;


    }

    @Override
    public void save(Message message) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(message);
    }
}
