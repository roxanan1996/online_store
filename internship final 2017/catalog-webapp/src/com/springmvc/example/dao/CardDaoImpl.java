package com.springmvc.example.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmvc.example.model.Card;

@Repository("cardDao")
public class CardDaoImpl implements CardDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Card> getAllCards() {
		return (List<Card>) sessionFactory.getCurrentSession().createCriteria(Card.class).list();
	}

	@Override
	public Card getCard(Integer cardId) {
		return (Card) sessionFactory.getCurrentSession().get(Card.class, cardId);
	}

}
