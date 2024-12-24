package com.main.sub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController{
	@GetMapping("/test")
	@ResponseBody
	public String Test() {
		return "Hello World!!";
	}
}
