package org.commkart.service;

import org.commkart.dto.LoginRequest;
import org.commkart.dto.LoginResponse;
import org.commkart.handler.TokenProvider;
import org.commkart.model.User;
import org.commkart.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public LoginResponse login(LoginRequest request) {
		LoginResponse response = null;
		try {
			User user = userRepo.findByEmail(request.getEmail());
			if(user != null) {
				if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
					response = new LoginResponse(tokenProvider.generateToken(user));
				}
				else {
					throw new Error("Password Incorrect");
				}
			}
			else {
				throw new Error("User not found");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
}
