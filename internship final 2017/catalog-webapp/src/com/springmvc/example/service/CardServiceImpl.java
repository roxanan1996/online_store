package com.springmvc.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.example.dao.CardDao;
import com.springmvc.example.model.Card;

@Service("cardService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;
	
	@Override
	public List<Card> getAllCards() {
		return cardDao.getAllCards();
	}

	@Override
	public Card getCard(Integer cardId) {
		return cardDao.getCard(cardId);
	}

}
