package com.company.webchat.service;

import com.company.webchat.entity.User;
import com.company.webchat.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
