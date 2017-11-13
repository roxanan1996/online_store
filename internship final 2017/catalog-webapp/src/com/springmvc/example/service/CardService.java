package com.springmvc.example.service;

import java.util.List;

import com.springmvc.example.model.Card;

public interface CardService {
	
	public List<Card> getAllCards();
	
	public Card getCard(Integer cardId);

}
