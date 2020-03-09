package com.aws.demo.controller;

import com.aws.demo.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/message",produces = MediaType.APPLICATION_JSON_VALUE)
	public Message getMessage() {
		System.out.println("getMessage:: Hello World!");
		return new Message("Hello World!");
	}

}