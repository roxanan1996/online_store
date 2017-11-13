package com.jaxrs.example.service;

import java.util.ArrayList;
import java.util.List;

import com.jaxrs.example.dao.CardDao;
import com.jaxrs.example.dao.CardDaoImpl;
import com.jaxrs.example.model.Card;

public class CardServiceImpl implements CardService {
	
	CardDao cardDao = new CardDaoImpl();
	
	public List<Card> getAllCards() {
		return this.cardDao.getAllCards();
	}
	
	public List<Card> getAllCardsPaginated(int offset, int limit) {
		List<Card> cardList = this.cardDao.getAllCards();
		if(offset + limit < cardList.size()) return cardList.subList(offset, offset + limit);
		else if (offset + limit > cardList.size() && offset + limit - cardList.size() < limit) return cardList.subList(offset, cardList.size());
		else return new ArrayList<Card>();
	}

	public Card getCard(String cardId) {
		return this.cardDao.getCard(Integer.parseInt(cardId));
	}
	
	public List<Card> getAllCardsForSeason(String season) {
		return this.cardDao.getAllCardsForSeason(season);
	}

	public List<Card> getAllCardsForSeasonPaginated(String season, int offset, int limit) {
		List<Card> cardList =  this.cardDao.getAllCardsForSeason(season);
		if (offset + limit > cardList.size()) return new ArrayList<Card>();
		return cardList.subList(offset, offset + limit);
	}

}
