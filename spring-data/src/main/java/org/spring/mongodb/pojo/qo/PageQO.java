package org.spring.mongodb.pojo.qo;

/**
 * Created by xiaoy on 2016/3/18.
 */
public class PageQO {

    private int pageNo;

    private int pageSize;

    private Object baseQO;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getBaseQO() {
        return baseQO;
    }

    public void setBaseQO(Object baseQO) {
        this.baseQO = baseQO;
    }
}
