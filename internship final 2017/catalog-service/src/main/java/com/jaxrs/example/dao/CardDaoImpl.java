package com.jaxrs.example.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.jaxrs.example.model.Card;

public class CardDaoImpl implements CardDao {

	private SessionFactory sessionFactory;

	public CardDaoImpl() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Card> getAllCards() {
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		List<Card> cardList = sessionFactory.getCurrentSession().createCriteria(Card.class).list();
		tx.commit();
		return cardList;
	}

	@SuppressWarnings("unchecked")
	public List<Card> getAllCardsForSeason(String season) {
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		List<Card> cardList = (List<Card>) sessionFactory.getCurrentSession().createCriteria(Card.class)
				.add(Restrictions.like("season", season));
		tx.commit();
		return cardList;
	}

	public Card getCard(Integer cardId) {
		return (Card) sessionFactory.getCurrentSession().get(Card.class, cardId);
	}

}
