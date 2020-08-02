package event;

public class SnowListener implements WeatherListener {
    @Override
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        if(weatherEvent instanceof SnowEvent){
            System.out.println("hello " + weatherEvent.getWeather());
        }
    }
}
