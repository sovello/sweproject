package com.mumscheduler.app.config.datainitializer;

import com.mumscheduler.security.model.Role;
import com.mumscheduler.security.model.User;
import com.mumscheduler.security.service.RoleService;
import com.mumscheduler.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationRunner {

	//@Autowired
	private UserService userService;
	
	//@Autowired
	private RoleService roleService;
	
	@Autowired
	public InitialDataLoader(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		/**
		 * Initialize roles
		 */
		roleService.save(new Role("ADMIN"));
		roleService.save(new Role("FACULTY"));
		roleService.save(new Role("STUDENT"));
		
		/**
		 * Initialize the database with super user admin
		 */
		User user = new User("Admin", "Admin", "admin@mumsched.com", "admin");
		userService.save(user, "ADMIN");
		
	}
	
}
