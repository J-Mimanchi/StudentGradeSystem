package com.paged_information;

import java.util.Vector;

//获得或设置对象数组以及获取或设置总条数
public class TableDTO {
    private Vector<Vector<Object>> data;
    private int totalCount;

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
