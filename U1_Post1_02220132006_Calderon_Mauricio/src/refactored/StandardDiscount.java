package refactored;

public class StandardDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        if (total > 5000) return total * 0.85; // 15% descuento
        if (total > 1000) return total * 0.90; // 10% descuento
        return total;
    }
}