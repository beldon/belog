package belog.pojo.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Beldon
 */
public abstract class EventSupport<T> extends ApplicationEvent implements Event {
    protected Action action;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public EventSupport(T source) {
        super(source);
    }

    @Override
    public T getSource(){
        return (T) source;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
