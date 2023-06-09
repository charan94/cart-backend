package org.commkart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
	
	@GetMapping("/auth")
	public ResponseEntity<String> handleAuthFallback() {
		return new ResponseEntity<String>("Unavailable", HttpStatus.BAD_REQUEST);
	}
}
