package com.springmvc.example.dao;

import java.util.List;

import com.springmvc.example.model.Stock;

public interface StockDao {

	public List<Stock> getAllStocks();
	
	public Stock getStockForCard(Integer cardId);
	
	public void updateStockForCard(Integer cardId, int quantity);
	
}
