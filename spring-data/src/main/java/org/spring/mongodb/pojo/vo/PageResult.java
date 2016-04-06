package org.spring.mongodb.pojo.vo;

import org.spring.mongodb.model.BaseModel;

import java.util.List;

/**
 * Created by xiaoy on 2016/3/18.
 */
public class PageResult<T> {

    private int pageNo;

    private int pageSize;

    private long totalCount;

    private Long pageCount;

    private List<T> list;

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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        if(pageCount == null){
            if(pageSize!=0&&totalCount!=0){
                pageCount = (totalCount-1)/pageSize +1;
            }else{
                pageCount = 1l;
            }
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getPageCount() {
        return pageCount;
    }
}
