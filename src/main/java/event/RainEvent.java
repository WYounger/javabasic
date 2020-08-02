package event;

public class RainEvent implements WeatherEvent {
    @Override
    public String getWeather() {
        return "rain";
    }
}
