package com.company.webchat.dao;

import com.company.webchat.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
