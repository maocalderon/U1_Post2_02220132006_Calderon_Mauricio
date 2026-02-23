package model;

public class Food extends Product {
    private int daysToExpire;

    public Food(String name, double price, int days) {
        super(name, price);
        this.daysToExpire = days;
    }

    @Override
    public double calculateShipping() {
        return 2000.0;
    }

    @Override
    public String getDescription() {
        return name + " [Alimento] Vence en: " + daysToExpire + " días";
    }
}