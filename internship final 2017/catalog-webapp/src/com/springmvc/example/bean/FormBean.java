package com.springmvc.example.bean;

import javax.validation.constraints.Pattern;

public class FormBean {
	 
	@Pattern(regexp = "[0-9]+")
	public String cardId;	
	
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Quantity field must be a positive integer!")
	public String quantity;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
