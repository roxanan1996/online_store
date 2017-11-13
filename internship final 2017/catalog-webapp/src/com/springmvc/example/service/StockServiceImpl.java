package com.springmvc.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.example.dao.StockDao;
import com.springmvc.example.model.Stock;

@Service("stockService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;
	
	@Override
	public List<Stock> getStockForAllCards() {
		return stockDao.getAllStocks();
	}

	@Override
	public Stock getStockForCard(Integer cardId) {
		return stockDao.getStockForCard(cardId);
	}
	
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateStockForCard(Integer cardId, int quantity) {
		stockDao.updateStockForCard(cardId, quantity);
	}

}
