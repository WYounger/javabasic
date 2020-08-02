package event;

public class SnowEvent implements WeatherEvent{
    @Override
    public String getWeather() {
        return "snow";
    }
}
