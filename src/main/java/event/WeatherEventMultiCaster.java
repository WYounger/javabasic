package event;

public class WeatherEventMultiCaster extends AbstractEventMultiCaster {
    @Override
    protected void doEnd() {
        System.out.println("do end");
    }

    @Override
    protected void doStart() {
        System.out.println("do start");
    }
}
