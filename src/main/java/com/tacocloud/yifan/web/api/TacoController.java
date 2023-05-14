package com.tacocloud.yifan.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tacocloud.yifan.tacos.Taco;
import com.tacocloud.yifan.tacos.TacoOrder;
import com.tacocloud.yifan.tacos.data.OrderRepository;
import com.tacocloud.yifan.tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")  // 處理針對/api/tacos的需求
@CrossOrigin("http://tacocloud:8080")
public class TacoController {
	
	@Autowired
	private TacoRepository tacoRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping(params =  "recent")
	public Iterable<Taco> recentTacos(){
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepository.findAll(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> TacoById(@PathVariable("id") Integer id){
		Optional<Taco> optTaco = tacoRepository.findById(id);
		
		if (optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	// 正常會回傳HTTP200，新增創建資源的描述給前端
	@ResponseStatus(code = HttpStatus.CREATED)
	// @RequestBody: 能將請求的JSON與Taco VO綁定在一起
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepository.save(taco);
	}
	
	@PutMapping(path = "/{orderId}", consumes = "application/json")
	public TacoOrder putOrder(@PathVariable("orderId") Integer id, @RequestBody TacoOrder tacoOrder) {
		tacoOrder.setId(id);
		return tacoRepository.save(tacoOrder);
	}
	
	@PatchMapping(path = "{orderId}", consumes = "application/json")
	public TacoOrder patchOrder(@PathVariable("orderId") Integer id, @RequestBody TacoOrder tacoOrder) {
		
		TacoOrder order = orderRepository.findById(id).get();
		
		if (tacoOrder.getDeliveryCity() != null) {
			order.setDeliveryCity(tacoOrder.getDeliveryCity());
		}
		
		if (tacoOrder.getDeliveryStreet() != null) {
			order.setDeliveryStreet(tacoOrder.getDeliveryStreet());
		}
		
		return orderRepository.save(order);
	}
	
	@DeleteMapping("/{orderId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Integer id) {
		try {
			orderRepository.deleteById(id);			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}
