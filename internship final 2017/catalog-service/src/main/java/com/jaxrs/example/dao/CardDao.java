package com.jaxrs.example.dao;

import java.util.List;

import com.jaxrs.example.model.Card;

public interface CardDao {

	public List<Card> getAllCards();
	
	public List<Card> getAllCardsForSeason(String season);
	
	public Card getCard(Integer cardId);
	
}
