package model;

public class Clothing extends Product {
    private String size;

    public Clothing(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public double calculateShipping() {
        return 5000.0;
    }

    @Override
    public String getDescription() {
        return name + " [Ropa] Talla: " + size;
    }
}