package event;

public class EventTest {
    public static void main(String[] args) {

        WeatherEventMultiCaster caster = new WeatherEventMultiCaster();

        SnowListener snowListener = new SnowListener();
        RainListener rainListener = new RainListener();

        SnowEvent snowEvent = new SnowEvent();
        RainEvent rainEvent = new RainEvent();

        caster.addListener(snowListener);
        caster.addListener(rainListener);

        caster.multiCastEvent(snowEvent);
        caster.multiCastEvent(rainEvent);
        caster.removeListener(snowListener);
        caster.multiCastEvent(snowEvent);
    }
}
/**
 * 监听器模式要素:
 * 事件 监听器 广播器 触发机制
 * 广播器将事件和监听器结合起来
 */
