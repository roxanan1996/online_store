package com.springmvc.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.example.model.Card;
import com.springmvc.example.model.Stock;
import com.springmvc.example.bean.CardBean;
import com.springmvc.example.bean.FormBean;
import com.springmvc.example.bean.ShoppingCartBean;
import com.springmvc.example.bean.ShoppingCartItemBean;
import com.springmvc.example.bean.StockBean;
import com.springmvc.example.exception.CustomException;
import com.springmvc.example.service.CardService;
import com.springmvc.example.service.StockService;
import com.springmvc.example.validator.CustomValidator;

@Controller
@Scope("request")
public class CardViewController {

	@Autowired
	private ShoppingCartBean cart;

	@Autowired
	private CardService cardService;

	@Autowired
	private StockService stockService;

	@Autowired
	CustomValidator customValidator;

	@RequestMapping(value = "/cardView/card/{cardId}", method = RequestMethod.GET)
	public ModelAndView getCardView(@PathVariable("cardId") Integer cardId) {

		Card card = cardService.getCard(cardId);
		if(card == null) {
			throw new CustomException("404", "Card not found");
		}
		
		CardBean cardBean = new CardBean();
		cardBean.setId(card.getCardId());
		cardBean.setTitle(card.getTitle());
		cardBean.setSeason(card.getSeason());
		cardBean.setPrice(card.getPrice());
		cardBean.setDescription(card.getDescription());
		cardBean.setThumbnailUrl(card.getThumbnailUrl());
		cardBean.setImageUrl(card.getImageUrl());

		Stock stock = stockService.getStockForCard(cardId);	
		if(stock == null) {
			throw new CustomException("", "Product unavailable");
		}
		StockBean stockBean = new StockBean();
		stockBean.setCardId(card.getCardId());
		stockBean.setQuantity(stock.getQuantity());
		
		FormBean formBean = new FormBean();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cardBean", cardBean);
		model.put("stockBean", stockBean);
		model.put("formBean", formBean);
		return new ModelAndView("cardView", model);
	}

	@RequestMapping(value = "/cardView/card/{cardId}", method = RequestMethod.POST)
	public ModelAndView addCardToShoppingCart(@Valid FormBean formBean, @PathVariable("cardId") Integer cardId,
			BindingResult result) {

		customValidator.validate(formBean, result);
		if (result.hasErrors()) {
			return new ModelAndView("cardView");
		}

		int id = Integer.parseInt(formBean.getCardId());
		int quantity = Integer.parseInt(formBean.getQuantity());
		
		Card card = cardService.getCard(id);
		if(card == null) {
			throw new CustomException("404", "Card not found");
		}	
		CardBean cardBean = new CardBean();
		cardBean.setId(card.getCardId());
		cardBean.setTitle(card.getTitle());
		cardBean.setSeason(card.getSeason());
		cardBean.setPrice(card.getPrice());
		cardBean.setDescription(card.getDescription());
		cardBean.setThumbnailUrl(card.getThumbnailUrl());
		cardBean.setImageUrl(card.getImageUrl());	
		
		Stock stock = stockService.getStockForCard(id);		
		StockBean stockBean = new StockBean();
		stockBean.setCardId(card.getCardId());
		stockBean.setQuantity(stock.getQuantity());
		
		ShoppingCartItemBean cartItem = new ShoppingCartItemBean();
		cartItem.setCard(cardBean);
		cartItem.setQuantity(quantity);
		
		cart.add(cartItem);
		
		formBean = new FormBean();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cardBean", cardBean);
		model.put("stockBean", stockBean);
		model.put("formBean", formBean);
		return new ModelAndView("cardView", model);
		
	}
	
	@ExceptionHandler(CustomException.class)
	public ModelAndView handleCustomException(CustomException e) {

		ModelAndView model = new ModelAndView("error");
		model.addObject("exception", e);
		return model;
	}
}
