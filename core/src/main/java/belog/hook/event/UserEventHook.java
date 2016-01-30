package belog.hook.event;


import belog.pojo.event.UserEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Beldon
 */
@Component("userEventHook")
public class UserEventHook extends EventHookSupport<UserEvent> {

    @Override
    public void init() {
        this.plugins = getPlugins(UserEventListener.class);
    }

    public void onApplicationEvent(UserEvent event) {
        super.onEvent(event);
    }

    public interface UserEventListener extends EventListener<UserEvent> {

    }
}
