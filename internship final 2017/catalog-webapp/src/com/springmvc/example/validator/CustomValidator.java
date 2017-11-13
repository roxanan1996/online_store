package com.springmvc.example.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
import com.springmvc.example.bean.FormBean;
import com.springmvc.example.model.Stock;
import com.springmvc.example.service.StockService;
 
@Component
public class CustomValidator implements Validator {
	
	@Autowired
	private StockService stockService;
 
    public boolean supports(Class<?> clazz) {
        return FormBean.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object target, Errors errors) {
    	
    	FormBean formData = (FormBean)target;
    	
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardId", "Invalid cardId");
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "Quantity field must not be empty");
    	
    	Integer cardId = Integer.parseInt(formData.getCardId());
    	int quantity = Integer.parseInt(formData.getQuantity());
    	
    	//Business validation
    	Stock stock = stockService.getStockForCard(cardId);
    	if(stock.getQuantity() < quantity) {
    		errors.rejectValue("quantity", "To Big!");
    	}
    }
 
}