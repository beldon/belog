package belog.hook.event;

import belog.pojo.event.CategorysEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Beldon
 */
@Component("categorysEventHook")
public class CategorysEventHook extends EventHookSupport<CategorysEvent> {
    @Override
    public void init() {
        this.plugins = getPlugins(CategorysEventListener.class);
    }

    public void onApplicationEvent(CategorysEvent event) {
        super.onEvent(event);
    }

    public interface CategorysEventListener extends EventListener<CategorysEvent> {

    }
}
