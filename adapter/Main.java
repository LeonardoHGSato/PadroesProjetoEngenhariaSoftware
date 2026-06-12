/**
 * Padrão de Projeto: Adapter (Estrutural)
 * Exemplo: Adaptar um sensor de temperatura legado, que retorna a
 * temperatura em Fahrenheit, para uma interface que o sistema novo
 * espera (temperatura em Celsius).
 *
 * Implementado com auxílio do Claude (Anthropic).
 * Baseado na explicação do padrão disponível em:
 * https://refactoring.guru/pt-br/design-patterns/adapter
 */
public class Main {

    public static void main(String[] args) {
        LegacyFahrenheitSensor legacySensor = new LegacyFahrenheitSensor();
        TemperatureSensor adapter = new FahrenheitToCelsiusAdapter(legacySensor);

        displayTemperature(adapter);
    }

    // Código cliente: só conhece a interface nova (TemperatureSensor)
    private static void displayTemperature(TemperatureSensor sensor) {
        System.out.printf("Temperatura atual: %.2f°C%n", sensor.getTemperatureCelsius());
    }
}
