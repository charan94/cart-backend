package org.commkart.service;

import java.util.Arrays;

import org.commkart.model.Role;
import org.commkart.model.User;
import org.commkart.repo.RolesRepo;
import org.commkart.repo.UserRepo;
import org.commkart.utils.CKUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RolesRepo roleRepo;

	@Autowired
	private MailService mailService;

	public void register(User user) {
		try {
			String pass = CKUtils.generatePassword(10);
			user.setPassword(passwordEncoder.encode(pass));
			Role userRole = roleRepo.findByName("ROLE_USER");
			user.setRoles(Arrays.asList(userRole));
			userRepo.save(user);
			mailService.sendEmail(user, pass);
		} catch (Exception ex) {
			throw new Error("Error in Registering user");
		}
	}

}
