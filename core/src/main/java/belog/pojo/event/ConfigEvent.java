package belog.pojo.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Beldon
 */
public class ConfigEvent extends ApplicationEvent implements Event {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public ConfigEvent(Object source) {
        super(source);
    }
}
