package com.jaxrs.example.service;

import java.util.List;

import com.jaxrs.example.model.Card;

public interface CardService {
	
	public List<Card> getAllCards();
	
	public Card getCard(String cardId);

	List<Card> getAllCardsPaginated(int offset, int limit);

	public List<Card> getAllCardsForSeasonPaginated(String season, int offset, int limit);

	public List<Card> getAllCardsForSeason(String season);

}
