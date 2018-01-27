package com.mumscheduler.security.service;

import com.mumscheduler.security.model.Role;
import com.mumscheduler.security.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	public Role save(Role role) {
		if( roleRepository.findByRole(role.getRole()) == null) {
			return roleRepository.save(role);
		}
		else {
			System.out.println(String.format("Tried to add role %s which already exists", role.getRole()));
			return null;
		}
	}
}
