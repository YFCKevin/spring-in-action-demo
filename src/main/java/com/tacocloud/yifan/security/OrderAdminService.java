package com.tacocloud.yifan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.tacocloud.yifan.tacos.data.OrderRepository;

@Service
public class OrderAdminService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	// PreAuthorize會檢查user是否具有admin權限。有(true)的話，方法被允許使用
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAllOrder() {
		orderRepo.deleteAll();
	}
}
