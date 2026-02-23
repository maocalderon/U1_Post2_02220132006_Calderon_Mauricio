package strategy;

public class BlackFridayPricing implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double price) { return price * 0.70; }
    @Override
    public String getDescription() { return "Descuento Black Friday (30%)"; }
}