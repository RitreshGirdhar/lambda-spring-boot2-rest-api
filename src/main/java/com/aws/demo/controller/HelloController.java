package com.aws.demo.controller;

import com.aws.demo.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/message",produces = MediaType.APPLICATION_JSON_VALUE)
	public Message getMessage() {
		System.out.println("getMessage:: Hello World!");
		return new Message("Hello World!");
	}

	@PostMapping(value = "/message",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message postMessage(@RequestBody Message message) {
		System.out.println("postMessage::"+message.getText());
		return new Message("Hello World!"+ message.getText());
	}

}