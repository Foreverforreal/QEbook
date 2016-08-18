package com.feifanuniv.collegeebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/Home")
	public String toHome(){
		return "home";
	}
}

