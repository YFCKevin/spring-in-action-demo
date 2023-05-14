package com.tacocloud.yifan.web;

import java.awt.print.Pageable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tacocloud.yifan.security.User;
import com.tacocloud.yifan.tacos.TacoOrder;
import com.tacocloud.yifan.tacos.data.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private orderProps orderProps;
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	// 列出認證user過去的訂單
	@GetMapping
	public String orderForUser(@AuthenticationPrincipal User user, Model model) {
		
		Pageable pageable = (Pageable) PageRequest.of(0, orderProps.getPageSize()); 
		model.addAllAttributes(orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
		
		return "orderList";
		
	}
	
	// 使用@AuthenticationPrincipal的目的是為了將主體注入到方法參數中，使開發人員能夠方便地訪問當前用戶的身份信息，且不需要轉換型別。
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, 
			@AuthenticationPrincipal User user) {
		
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		order.setUser(user);
		
		orderRepo.save(order);
		
//		log.info("Order submitted: {}", order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}
