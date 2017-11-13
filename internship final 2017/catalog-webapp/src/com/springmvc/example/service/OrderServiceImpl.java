package com.springmvc.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.example.dao.OrderDao;
import com.springmvc.example.dao.OrderItemDao;
import com.springmvc.example.model.Order;
import com.springmvc.example.model.OrderItem;

@Service("orderService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public void save(Order o) {
		orderDao.save(o);
	}

	@Override
	public void add(Order order, OrderItem orderItem) {
		orderItem.setOrder(order);
		orderItemDao.save(orderItem);
	}

}
