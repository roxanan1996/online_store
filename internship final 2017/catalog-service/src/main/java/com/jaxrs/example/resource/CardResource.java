package com.jaxrs.example.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.jaxrs.example.model.Card;
import com.jaxrs.example.service.CardService;
import com.jaxrs.example.service.CardServiceImpl;

// follow the steps here if you have the NoClassDefFoundError
// https://stackoverflow.com/questions/33722764/glassfish-error-when-producing-json


@Provider 
@Path("/cards")
public class CardResource {
    @Context
    private UriInfo context;
    @Context
    private HttpHeaders headers;
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getCards(@QueryParam("start") int offset, @QueryParam("size") int limit) {
    	CardService cardService = new CardServiceImpl();
    	if (offset >= 0 && limit > 0) {
    		return cardService.getAllCardsPaginated(offset, limit);
    	}
    	return cardService.getAllCards();
    }
        
    @GET
    @Path("/seasons/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getCardsForSeason(@PathParam("season") String season, @QueryParam("start") int offset, @QueryParam("size") int limit) {
    	CardService cardService = new CardServiceImpl();
    	if (season.matches("Winter|Autumn|Spring|Summer")) {
        	if (offset > 0 && limit > 0) {
        		return cardService.getAllCardsForSeasonPaginated(season, offset, limit);
        	}
        	return cardService.getAllCardsForSeason(season);
    	}
    	return new ArrayList<Card>();
    }
    
    @GET
    @Path("/{cardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Card getCard(@PathParam("cardId") String id) {
    	CardService cardService = new CardServiceImpl();
    	Card card = cardService.getCard(id);
    	return card;
    }
}
