// Estratégia concreta: frete padrão (mais barato, mais lento)
public class StandardShipping implements ShippingStrategy {

    @Override
    public double calculate(double weight, double distance) {
        return weight * 0.5 + distance * 0.1;
    }
}
