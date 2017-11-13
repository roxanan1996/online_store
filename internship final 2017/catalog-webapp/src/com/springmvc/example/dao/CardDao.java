package com.springmvc.example.dao;

import java.util.List;

import com.springmvc.example.model.Card;

public interface CardDao {

	public List<Card> getAllCards();
	
	public Card getCard(Integer cardId);
	
}
