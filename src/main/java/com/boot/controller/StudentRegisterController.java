package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.model.StudentRegistration;
import com.boot.model.StudentRegistrationReply;

@Controller
public class StudentRegisterController {

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	@ResponseBody
	public StudentRegistrationReply processReg(@RequestBody final StudentRegistration studentRegistration) {
		final StudentRegistrationReply studentRegistrationReply = new StudentRegistrationReply();
		studentRegistrationReply.setAge(studentRegistration.getAge());
		studentRegistrationReply.setName(studentRegistration.getName());
		studentRegistrationReply.setRegistrationNumber("09");
		studentRegistrationReply.setRegistrationStatus("THINKING");
		return studentRegistrationReply;
	}

}
