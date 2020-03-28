package org.commkart.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class CoreController {

	@GetMapping("accounts")
	public Mono<HashMap<String, String>> getAccounts() {
		return Mono.just(new HashMap<String, String>());
	}
}
