package com.aws.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String hello(@PathVariable String id) {
		return "Hello World!";
	}
}