package org.commkart.controller;

import org.commkart.dto.ApiResponse;
import org.commkart.dto.LoginRequest;
import org.commkart.dto.LoginResponse;
import org.commkart.model.User;
import org.commkart.service.AuthService;
import org.commkart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Controller
public class MainController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	@ResponseBody
	public Mono<ApiResponse> authorizeLogin(ServerWebExchange exchange) {
		Mono<MultiValueMap<String, String>> formData = exchange.getFormData();
		return formData.flatMap(data -> {
			if (data != null) {
				LoginRequest request = new LoginRequest();
				request.setEmail(data.getFirst("email").toString());
				request.setPassword(data.getFirst("password").toString());
				try {
					LoginResponse loginResponse = authService.login(request);
					if (loginResponse != null) {
						return Mono.just(new ApiResponse(200, "Login Success", loginResponse));
					}
				} catch (Exception ex) {
					return Mono.just(new ApiResponse(401, ex.getMessage(), null));
				}
			}
			return Mono.just(new ApiResponse(401, "Error in login", null));
		});
	}
	
	@PostMapping("/register")
	@ResponseBody
	public Mono<ApiResponse> register(ServerWebExchange exchange) {
		Mono<MultiValueMap<String, String>> formData = exchange.getFormData();
		return formData.flatMap(data -> {
			if(data != null) {
				User user = new User();
				user.setTitle(data.getFirst("title"));
				user.setFirstName(data.getFirst("firstName"));
				user.setLastName(data.getFirst("lastName"));
				user.setEmail(data.getFirst("email"));
				user.setPhone(data.getFirst("phone"));
				user.setBusinessInterest(data.getFirst("businessInterest"));
				user.setCompanyName(data.getFirst("companyName"));
				user.setEnabled(true);
				try {
					userService.register(user);
				}
				catch(Exception ex) {
					return Mono.just(new ApiResponse(500, ex.getMessage(), null));
				}
				
				return Mono.just(new ApiResponse(200, "Registered Successfully, Please check your mail", null));
			}
			return Mono.just(new ApiResponse(401, "Registration Failed", null));
		});
	}
}
