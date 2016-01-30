package belog.hook.event;

import belog.pojo.event.RoleEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Beldon
 */
@Component("roleEventHook")
public class RoleEventHook extends EventHookSupport<RoleEvent> {
    @Override
    public void init() {
        this.plugins = getPlugins(RoleEventListener.class);
    }

    public void onApplicationEvent(RoleEvent event) {
        super.onEvent(event);
    }

    public interface RoleEventListener extends EventListener<RoleEvent> {

    }
}
