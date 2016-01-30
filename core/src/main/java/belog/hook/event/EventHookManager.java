package belog.hook.event;

import belog.context.AppContext;
import belog.hook.HookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Event钩子总管理器，管理所有钩子管理器
 *
 * @author Beldon
 */
@Component("eventHookManager")
public class EventHookManager extends HookManager<EventHook> implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private AppContext appContext;

    @Override
//    @PostConstruct
    protected void init() {
        Map<String, EventHook> map = appContext.getContexts().getBeansOfType(EventHook.class);
        Iterator<Map.Entry<String, EventHook>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, EventHook> entry = it.next();
            EventHook eventHook = entry.getValue();
//            addEventHook(eventHook.getEventClass().getName(), eventHook);
            addHook(eventHook.getEventClass().getName(), eventHook);
        }
    }

    public void onApplicationEvent(ApplicationEvent event) {
        String eventKey = event.getClass().getName();
        if (hookMap.containsKey(eventKey)) {
            Set<EventHook> hooks = hookMap.get(eventKey);
            for (EventHook hook : hooks) {
                hook.onApplicationEvent(event);
            }
        }
    }
}
