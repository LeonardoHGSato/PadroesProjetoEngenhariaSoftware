/**
 * Padrão de Projeto: Strategy (Comportamental)
 * Exemplo: Cálculo de frete usando diferentes estratégias de envio.
 *
 * Implementado com auxílio do Claude (Anthropic).
 * Baseado na explicação do padrão disponível em:
 * https://refactoring.guru/pt-br/design-patterns/strategy
 */
public class Main {

    public static void main(String[] args) {
        Order order = new Order(5, 100, new StandardShipping());
        System.out.printf("Frete padrão:   R$ %.2f%n", order.getShippingCost());

        order.setStrategy(new ExpressShipping());
        System.out.printf("Frete expresso: R$ %.2f%n", order.getShippingCost());

        order.setStrategy(new PickupShipping());
        System.out.printf("Frete retirada: R$ %.2f%n", order.getShippingCost());
    }
}
