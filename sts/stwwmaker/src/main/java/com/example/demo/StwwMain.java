package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StwwMain {
	@RequestMapping(value="/")
	public String index(Model model) {
		return "index1";
	}
}
