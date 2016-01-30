package belog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon on 2015/9/12.
 */
public class PageModel {
    /**
     * 数据列表
     */
    private List list = new ArrayList();
    /**
     * 当前页数，默认为第一页
     */
    private int currentPage = 1;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 记录总数,或者需要查找的总数
     */
    private int totalCount;
    /**
     * 每页显示数，默认为10条
     */
    private int pageSize = 8;

    public PageModel() {
    }

    public PageModel(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return this.currentPage < 1 ? 1 : this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage < 1 ? 1 : currentPage;
    }

    public int getTotalPage() {
        if (this.totalCount < 0) {
            return -1;
        } else {
            int count = this.totalCount / this.pageSize;
            if (this.totalCount % this.pageSize > 0) {
                ++count;
            }

            return count;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? 1 : pageSize;
    }

    public int getFirst() {
        return this.currentPage * this.pageSize - this.pageSize + 1;
    }

    public int getBeginIndex() {
        return (this.currentPage - 1) * this.pageSize;
    }
}
