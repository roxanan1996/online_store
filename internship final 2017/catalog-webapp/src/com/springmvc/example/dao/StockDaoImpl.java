package com.springmvc.example.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.example.model.Stock;

@Repository("stockDao")
public class StockDaoImpl implements StockDao {
	
	@Autowired
	private SessionFactory sessionFactory;							

	@Override
	@SuppressWarnings("unchecked")
	public List<Stock> getAllStocks() {
		return (List<Stock>) sessionFactory.getCurrentSession().createCriteria(Stock.class).list();
	}

	@Override
	public Stock getStockForCard(Integer cardId) {
		return (Stock) sessionFactory.getCurrentSession().get(Stock.class, cardId);
	}

	@Override
	public void updateStockForCard(Integer cardId, int quantity) {
		Stock stock = (Stock) sessionFactory.getCurrentSession().get(Stock.class, cardId);
		stock.setQuantity(quantity);
		sessionFactory.getCurrentSession().update(stock);
	}

}
