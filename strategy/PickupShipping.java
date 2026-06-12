// Estratégia concreta: retirada na loja (sem custo)
public class PickupShipping implements ShippingStrategy {

    @Override
    public double calculate(double weight, double distance) {
        return 0.0;
    }
}
