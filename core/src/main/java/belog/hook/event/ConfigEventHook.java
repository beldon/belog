package belog.hook.event;


import belog.pojo.event.ConfigEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Beldon
 */
@Component("configEventHook")
public class ConfigEventHook extends EventHookSupport<ConfigEvent> {
    @Override
    public void init() {
        this.plugins = getPlugins(ConfigEventListener.class);
    }

    public void onApplicationEvent(ConfigEvent event) {
        super.onEvent(event);
    }

    public interface ConfigEventListener extends EventListener<ConfigEvent> {

    }
}
