package com.springmvc.example.service;

import com.springmvc.example.model.Order;
import com.springmvc.example.model.OrderItem;

public interface OrderService {
	
	public void save(Order o);
	
	public void add(Order order, OrderItem orderItem);

}
