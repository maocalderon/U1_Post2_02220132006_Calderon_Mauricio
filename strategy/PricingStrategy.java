package strategy;

public interface PricingStrategy {
    double calculateFinalPrice(double originalPrice); // [cite: 125]
    String getDescription(); // [cite: 125]
}