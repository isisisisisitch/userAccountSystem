package com.bytetube.domain;

import java.util.List;

public class PageBean<E> {
    private int currentPage;//当前页面
    private int rows;//每页的显示条数
    private int totalCount;//总记录数
    private List<E> list;//每页的数据
    private int totalPage;//总页码数

    public PageBean() { }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", list=" + list +
                ", totalCount=" + totalCount +
                ", rows=" + rows +
                ", totalPage=" + totalPage +
                '}';
    }
}
