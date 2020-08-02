package event;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEventMultiCaster implements EventMuitiCaster{
    private List<WeatherListener> listeners = new ArrayList<>();

    @Override
    public void multiCastEvent(WeatherEvent weatherEvent){
        doStart();
        listeners.forEach(i -> i.onWeatherEvent(weatherEvent));
        doEnd();
    }
    @Override
    public void addListener(WeatherListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(WeatherListener listener) {
        listeners.remove(listener);
    }

    protected abstract void doEnd();

    protected abstract void doStart();


}
