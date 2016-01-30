package belog.hook.event;


import belog.pojo.event.ArticleEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Beldon
 */
@Component("articleEventHook")
public class ArticleEventHook extends EventHookSupport<ArticleEvent> {

    @Override
    public void init() {
        this.plugins = getPlugins(ArticleEventListener.class);
    }

    public void onApplicationEvent(ArticleEvent event) {
        super.onEvent(event);
    }

    public interface ArticleEventListener extends EventListener<ArticleEvent> {

    }
}
