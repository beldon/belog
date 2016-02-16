package belog.pojo.event;


import belog.pojo.vo.LinksVo;

/**
 * Created by Beldon
 */
public class LinkEvent extends EventSupport<LinksVo> {

    /**
     * Create a new ApplicationEvent.
     *
     * @param links the component that published the event (never {@code null})
     */
    public LinkEvent(LinksVo links) {
        super(links);
    }

    public LinkEvent(LinksVo links, Action action) {
        this(links);
        this.action = action;
    }

}
