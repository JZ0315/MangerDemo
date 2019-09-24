package it.jiazhi.pojo;

import java.util.List;

public class LimitPage<T> {
    //当前页码
    private Integer currentPage;  //current是当前的意思
    //每页显示条数
    private Integer rows;
    //总页数=(总记录数+每页显示条数-1)/每页显示条数
    private int allPage;
    private Integer allPagecount;
    //分页显示数据
    private List<T> pageDate;


    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getAllPage() {
        return allPage;
    }

    public void setAllPage(Integer allPage) {
        this.allPage = allPage;
    }

    public List<T> getPageDate() {
        return pageDate;
    }

    public void setPageDate(List<T> pageDate) {
        pageDate = pageDate;
    }

    public Integer getAllPagecount() {
        return allPagecount;
    }

    public void setAllPagecount(Integer allPagecount) {
        this.allPagecount = allPagecount;
    }

    @Override
    public String toString() {
        return "LimitPage{" +
                "currentPage=" + currentPage +
                ", rows=" + rows +
                ", allPage=" + allPage +
                ", allPagecount=" + allPagecount +
                ", PageDate=" + pageDate +
                '}';
    }
}
