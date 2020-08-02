package event;

public interface EventMuitiCaster {
    void multiCastEvent(WeatherEvent weatherEvent);
    void addListener(WeatherListener listener);
     void removeListener(WeatherListener listener);
}
