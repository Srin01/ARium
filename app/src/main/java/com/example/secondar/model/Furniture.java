package com.example.secondar.model;

public class Furniture {
    Integer imagePath;
    String name;
    String modelName;
    String price;

    public Furniture(Integer imagePath, String name, String modelName, String price) {
        this.imagePath = imagePath;
        this.name = name;
        this.modelName = modelName;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getImagePath() {
        return imagePath;
    }

    public void setImagePath(Integer imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
