package org.commkart.controller;

import org.commkart.dto.LoginRequest;
import org.commkart.dto.LoginResponse;
import org.commkart.model.User;
import org.commkart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Controller
public class MainController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	@ResponseBody
	public Mono<LoginResponse> authorizeLogin(ServerWebExchange exchange) {
		Mono<MultiValueMap<String, String>> formData = exchange.getFormData();
		return formData.flatMap(data -> {
			if (data != null) {
				LoginRequest request = new LoginRequest();
				request.setEmail(data.getFirst("email").toString());
				request.setPassword(data.getFirst("password").toString());
				LoginResponse loginResponse = authService.login(request);
				if (loginResponse != null) {
					return Mono.just(loginResponse);
				}
			}
			return Mono.just(new LoginResponse(null));
		});

	}
}
