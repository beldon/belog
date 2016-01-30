package belog.pojo.event;


import belog.pojo.vo.CategoryVo;

/**
 * Created by Beldon
 */
public class CategorysEvent extends EventSupport<CategoryVo> {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public CategorysEvent(CategoryVo source) {
        super(source);
    }


}
