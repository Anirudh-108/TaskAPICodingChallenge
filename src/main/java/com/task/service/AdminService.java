package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.model.Admin;
import com.task.model.UserInfo;
import com.task.repository.AdminRepository;
import com.task.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public Admin addAdmin(Admin admin) {
		UserInfo user = admin.getUser();
		user.setRole("ROLE_ADMIN");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);

		admin.setUser(user);

		return adminRepository.save(admin);
	}

}
