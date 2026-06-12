import java.util.Arrays;
import java.util.List;

/**
 * Padrão de Projeto: Factory Method (Criacional)
 * Exemplo: Criação de notificações (Email, SMS, Push) sem que o
 * código cliente precise conhecer as classes concretas.
 *
 * Implementado com auxílio do Claude (Anthropic).
 * Baseado na explicação do padrão disponível em:
 * https://refactoring.guru/pt-br/design-patterns/factory-method
 */
public class Main {

    public static void main(String[] args) {
        List<NotificationFactory> factories = Arrays.asList(
                new EmailNotificationFactory(),
                new SMSNotificationFactory(),
                new PushNotificationFactory()
        );

        for (NotificationFactory factory : factories) {
            factory.notify("Seu pedido foi confirmado!");
        }
    }
}
