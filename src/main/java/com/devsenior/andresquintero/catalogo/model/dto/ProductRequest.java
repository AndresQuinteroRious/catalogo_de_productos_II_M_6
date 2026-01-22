package com.devsenior.andresquintero.catalogo.model.dto;

import java.util.List;
import java.util.Map;

import com.devsenior.andresquintero.catalogo.model.shared.Tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
    @NotBlank(message = "el campo de nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "el campo de descripción no puede estar vacío")
    private String description;

    @NotNull(message = "el campo de precio no puede estar vacío")
    @Positive(message = "el precio debe ser un valor positivo")
    private double price;
    
    private Map<String, Object> especification;
    @NotEmpty(message = "el campo de etiquetas no puede estar vacío")
    private List<Tag> tags;

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
