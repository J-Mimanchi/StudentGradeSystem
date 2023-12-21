package com.paged_information;

//用来设置当前页 显示条数 以及搜索关键词
public class Demand {
    private int pageNow;
    private int pageSize;
    //查询词
    private String searchKey;
    private int start;

    public int getStart() {
        return (pageNow-1)*pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
