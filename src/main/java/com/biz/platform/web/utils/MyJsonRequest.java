package com.biz.platform.web.utils;

import java.util.List;

/**
 * Created by huangdonghua on 2017/12/14.
 */
public class MyJsonRequest <T> implements java.io.Serializable {

    private T gridData;
    private int pageNum;
    private int pageSize;

    public T getGridData() {
        return gridData;
    }

    public void setGridData(T gridData) {
        this.gridData = gridData;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
