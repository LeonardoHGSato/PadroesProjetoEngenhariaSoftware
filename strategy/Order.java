// Contexto: usa uma estratégia de frete, mas não conhece seus detalhes internos
public class Order {

    private final double weight;
    private final double distance;
    private ShippingStrategy strategy;

    public Order(double weight, double distance, ShippingStrategy strategy) {
        this.weight = weight;
        this.distance = distance;
        this.strategy = strategy;
    }

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double getShippingCost() {
        return strategy.calculate(weight, distance);
    }
}
