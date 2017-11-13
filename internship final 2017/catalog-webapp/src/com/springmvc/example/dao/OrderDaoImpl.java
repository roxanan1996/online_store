package com.springmvc.example.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.example.model.Order;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Order order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
	}

}
