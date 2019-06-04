

package com.persteenolsen.springbootrestmvcjpa.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This Controller is saving a welcome message in the model to be used in the JSP View
// It is using GetMapping to get url request by both /home and /welcome
@Controller
public class WelcomeController {
	
	private String helloworld = "";
		
	//@GetMapping("/")
	//@GetMapping({"/welcome", "/home"})
	@GetMapping({"/", "/welcome"})
	public String welcome(Map<String, Object> model) {

		helloworld = "Welcome to a Java Spring Boot REST MVC JPA Web application!";

		model.put("welcomemessage", helloworld);
				
		return "welcome";
	}
}