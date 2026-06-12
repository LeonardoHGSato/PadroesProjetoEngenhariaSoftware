// Adapter: "traduz" a interface da classe legada para a interface nova
public class FahrenheitToCelsiusAdapter implements TemperatureSensor {

    private final LegacyFahrenheitSensor legacySensor;

    public FahrenheitToCelsiusAdapter(LegacyFahrenheitSensor legacySensor) {
        this.legacySensor = legacySensor;
    }

    @Override
    public double getTemperatureCelsius() {
        double fahrenheit = legacySensor.getTemperatureFahrenheit();
        return (fahrenheit - 32) * 5 / 9;
    }
}
