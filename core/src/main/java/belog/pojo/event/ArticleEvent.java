package belog.pojo.event;


import belog.pojo.vo.ArticleVo;

/**
 * Created by Beldon
 */
public class ArticleEvent extends EventSupport<ArticleVo> {

    /**
     * Create a new ApplicationEvent.
     *
     * @param article the component that published the event (never {@code null})
     */
    public ArticleEvent(ArticleVo article) {
        super(article);
    }

    public ArticleEvent(ArticleVo article, Action action) {
        this(article);
        this.action = action;
    }

}
