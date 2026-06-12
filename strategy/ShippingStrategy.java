// Interface comum a todas as estratégias de cálculo de frete
public interface ShippingStrategy {
    double calculate(double weight, double distance);
}
