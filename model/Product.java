package model;

public abstract class Product {
    
    protected String name;
    protected double basePrice;

    public Product(String name, double price) {
        this.name = name;
        this.basePrice = price;
    }

    public abstract double calculateShipping();
    public abstract String getDescription();
    
    public double getBasePrice() {
        return basePrice;
    }
}