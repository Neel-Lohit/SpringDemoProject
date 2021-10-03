package com.example.springdemo.dao;

import com.example.springdemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
