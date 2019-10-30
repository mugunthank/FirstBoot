package com.boot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.StudentRegistration;
import com.boot.model.Welcome;

@RestController
public class WelcomeController {

	@RequestMapping(method = RequestMethod.POST, value = "/welcome/user")
	@ResponseBody
	public Welcome welcomeUser(@RequestBody final StudentRegistration studentRegistration) {
		return new Welcome("Nice come " + studentRegistration.getName());
	}

}
