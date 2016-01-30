package belog.pojo.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Beldon
 */
public class UserEvent extends ApplicationEvent implements Event {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public UserEvent(Object source) {
        super(source);
    }
}
