// Estratégia concreta: frete expresso (mais caro, mais rápido)
public class ExpressShipping implements ShippingStrategy {

    @Override
    public double calculate(double weight, double distance) {
        return weight * 1.0 + distance * 0.3 + 10;
    }
}
