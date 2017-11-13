package com.springmvc.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.example.model.Card;
import com.springmvc.example.model.Order;
import com.springmvc.example.model.OrderItem;
import com.springmvc.example.model.Stock;
import com.springmvc.example.bean.CardBean;
import com.springmvc.example.bean.FormBean;
import com.springmvc.example.bean.ShoppingCartBean;
import com.springmvc.example.bean.ShoppingCartItemBean;
import com.springmvc.example.bean.StockBean;
import com.springmvc.example.service.CardService;
import com.springmvc.example.service.OrderService;
import com.springmvc.example.service.StockService;
import com.springmvc.example.validator.CustomValidator;

@Controller
@Scope("request")
public class ShoppingCartViewController {

	@Autowired
	private ShoppingCartBean cart;

	@Autowired
	private CardService cardService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private StockService stockService;

	@Autowired
	CustomValidator customValidator;

	@RequestMapping(value = "/cartView", method = RequestMethod.GET)
	public ModelAndView getCartView(@RequestParam(value = "refereer") String refereerId) {

		List<ShoppingCartItemBean> cartItems = cart.getCartItems();
		float totalSum = cart.getTotal();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cartItems", cartItems);
		model.put("totalSum", totalSum);
		model.put("refereerId", refereerId);
		return new ModelAndView("cartView", model);
	}

	@RequestMapping(value = "/cartView/Checkout", method = RequestMethod.POST)
	public ModelAndView addCardToShoppingCart(@RequestParam("refereer") String refereerId) {

		Order order = new Order();
		order.setTotalSum(cart.getTotal());
		orderService.save(order);
		
		List<ShoppingCartItemBean> cartItems = cart.getCartItems();
		
		for(ShoppingCartItemBean cartItem : cartItems) 
		{		
			int cardId = cartItem.getCard().getId();
			Card card = cardService.getCard(cardId);
			int quantity = cartItem.getQuantity();
			
			OrderItem orderItem = new OrderItem();
			orderItem.setCard(card);
			orderItem.setQuantity(quantity);
			orderService.add(order, orderItem);
		}

		cart.removeAll();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cartItems", cart.getCartItems());
		model.put("totalSum", cart.getTotal());
		model.put("refereerId", refereerId);
		return new ModelAndView("cartView", model);
	}
	
	@RequestMapping(value = "/cartView/EmptyCart", method = RequestMethod.POST)
	public ModelAndView emptyShoppingCart(@RequestParam("refereer") String refereerId) {

		cart.removeAll();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cartItems", cart.getCartItems());
		model.put("totalSum", cart.getTotal());
		model.put("refereerId", refereerId);
		return new ModelAndView("cartView", model);
	}
}
