package com.amalan.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	public String status = "success";
	public String message = "Server is online!";
	@GetMapping("/")
	public Hello hello() {
		return new Hello();
	}
}