package com.devsenior.andresquintero.catalogo.model.dto;

import java.util.List;
import java.util.Map;

import com.devsenior.andresquintero.catalogo.model.shared.Tag;

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private Map<String, Object> especification;
    private List<Tag> tags;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Map<String, Object> getEspecification() {
        return especification;
    }
    public void setEspecification(Map<String, Object> especification) {
        this.especification = especification;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


}
