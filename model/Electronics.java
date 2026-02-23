package model;

public class Electronics extends Product {
    private int warrantyMonths;

    public Electronics(String name, double price, int warranty) {
        super(name, price);
        this.warrantyMonths = warranty;
    }

    @Override
    public double calculateShipping() {
        return basePrice * 0.05; // Aquí ya no debe salir error si Product tiene basePrice protected
    }

    @Override
    public String getDescription() {
        return name + " [Electrónica] Garantía: " + warrantyMonths + " meses";
    }
}