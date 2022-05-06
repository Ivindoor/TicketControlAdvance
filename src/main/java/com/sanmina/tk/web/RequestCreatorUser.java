package com.sanmina.tk.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestCreatorUser {

	@RequestMapping(value = "creatorUser")
	public String creatorUser() {
		return "UsersCreator/addUser";
	}
}
