package com.xxx.sms.model;

import java.util.List;

// 封装分页对象
public class Page<T> {
    // 当前页,默认是首页
    private int currentPage = 1;
    // 上一页
    private int prePage;
    // 下一页
    private int nextPage;
    // 每页显示数量
    public static int pageSize = 3;
    // 总页数
    private int totalPage;
    // 总条数
    private int total;
    // 每页数据
    private List<T> list;

    public Page(int currentPage, int total, List<T> list) {
        this.currentPage = currentPage;
        this.total = total;
        this.list = list;
        if (this.total % this.pageSize == 0) {
            this.totalPage = (this.total / this.pageSize);
        } else {
            this.totalPage = (this.total / this.pageSize + 1);
        }
        this.prePage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
        this.nextPage = (this.currentPage + 1 <= this.totalPage )? (this.currentPage + 1)
                : this.totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
