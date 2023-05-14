package com.tacocloud.yifan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private OrderAdminService orderAdminService;
	
	@PostMapping("/deleteOrders")
	public String deleteAllOrders() {
		orderAdminService.deleteAllOrder();
		return "redirect:/admin";
	}
}
