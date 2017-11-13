package com.example.andrei.catalog;

/**
 * Created by Andrei on 7/4/2017.
 */
public class Card {


    private Integer cardId;
    private String title;
    private String season;
    private float price;
    private String description;
    private String thumbnailUrl;
    private String imageUrl;

    public Card(Integer cardId, String title, String season, float price, String description, String thumbnailUrl, String imageUrl) {
        this.cardId = cardId;
        this.title = title;
        this.season = season;
        this.price = price;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.imageUrl = imageUrl;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
