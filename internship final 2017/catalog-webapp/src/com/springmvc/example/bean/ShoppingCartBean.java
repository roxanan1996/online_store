package com.springmvc.example.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ShoppingCartBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ShoppingCartItemBean> cartItemList;
	private float totalSum; 
	
	public List<ShoppingCartItemBean> getCartItems() {
		return cartItemList;
	}
	
	public float getTotal() {
		return totalSum;
	}
	
	public void add(ShoppingCartItemBean cartItem) {
		if(this.cartItemList == null) {
			this.cartItemList = new ArrayList<ShoppingCartItemBean>();
		}
		
		this.cartItemList.add(cartItem);
		totalSum += cartItem.getCard().getPrice() * cartItem.getQuantity();
	}
	
	public void remove(ShoppingCartItemBean cartItem) {
		this.cartItemList.remove(cartItem);
		totalSum -= cartItem.getCard().getPrice() * cartItem.getQuantity();
	}
	
	public void removeAll() {
		this.cartItemList.clear();
		this.totalSum = 0;
	}

}
