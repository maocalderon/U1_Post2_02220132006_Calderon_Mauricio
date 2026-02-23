package strategy;

public class RegularPricing implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double price) { return price; } // [cite: 130]
    @Override
    public String getDescription() { return "Precio regular (Sin descuento)"; } // [cite: 131]
}