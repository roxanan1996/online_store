package com.springmvc.example.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.example.model.OrderItem;

@Repository("orderItemDao")
public class OrderItemDaoImpl implements OrderItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void save(OrderItem orderItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(orderItem);
	}

}
