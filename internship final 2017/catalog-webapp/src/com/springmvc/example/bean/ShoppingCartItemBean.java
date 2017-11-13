package com.springmvc.example.bean;

import java.io.Serializable;

public class ShoppingCartItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public CardBean card;
	public int quantity;
	
	public CardBean getCard() {
		return card;
	}
	public void setCard(CardBean card) {
		this.card = card;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
}
