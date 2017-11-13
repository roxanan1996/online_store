package com.springmvc.example.service;

import java.util.List;

import com.springmvc.example.model.Stock;

public interface StockService {
	
	public List<Stock> getStockForAllCards();
	
	public Stock getStockForCard(Integer cardId);

	void updateStockForCard(Integer cardId, int quantity);

}
