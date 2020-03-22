package com.company.webchat.dao;

import com.company.webchat.entity.Role;

public interface RoleDao {

	Role findRoleByName(String theRoleName);
	
}
