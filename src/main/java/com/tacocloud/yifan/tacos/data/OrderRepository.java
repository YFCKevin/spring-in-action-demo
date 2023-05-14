package com.tacocloud.yifan.tacos.data;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;

import com.tacocloud.yifan.security.User;
import com.tacocloud.yifan.tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Integer>{
	
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
//	TacoOrder save(TacoOrder order);
	
//	List<TacoOrder> findByDeliveryZip(String deliveryZip);
//	
//	List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
//	
//	List<TacoOrder> findByDeliveryCityOrdetByDeliveryTo(String deliveryCity);
}
