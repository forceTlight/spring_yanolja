package com.yanolja.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {
	
	@GetMapping("/test")
	public String projectInfo() {
		log.debug("/info start");
		log.info("hello");
		return "success";
	}
}
