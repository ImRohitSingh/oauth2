package com.test.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

	@GetMapping(path = "/public")
	public String publicPage() {
		return "Public Page";
	}

	@GetMapping(path = "/private")
	public String privatePage() {
		return "Private Page";
	}

	@GetMapping(path = "/private/param")
	public String privatePage(@RequestParam(name = "privateParam", required = true) String param) {
		return "Private Page" + param;
	}

	@GetMapping(path = "/admin")
	public String adminPage() {
		return "Administrator Page";
	}

	@PostMapping(path = "/admin")
	public String adminPage(@RequestBody(required = true) String param) {
		return "Administrator Page" + param;
	}

}
