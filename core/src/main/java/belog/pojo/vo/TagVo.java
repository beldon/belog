package belog.pojo.vo;

/**
 * Created by Beldon
 */
public class TagVo {
    private long id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 分组
     */
    private int termGroup = 0;

    /**
     * 文章数统计
     */
    private long count = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(int termGroup) {
        this.termGroup = termGroup;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
