package com.sxtljx.util;

public class PageUtil {
    //总条数
    private int count;
    //当前页
    private int curPage;
    //每页显示条数
    private int pageRow=20;
    //起始行
    private int startRow;
    //总页数
    private int pageCount;
    //首页
    private int firstPage=1;
    //尾页
    private int lastPage;
    //上一页
    private int prevPage;
    //下一页
    private int nextPage;
    //起始导航
    private int beginNav;
    //结束导航
    private int endNav;

    public PageUtil(int count, int curPage) {
        this.count = count;
        this.curPage = curPage;
        this.startRow = (curPage-1)*this.pageRow;
        this.pageCount = this.count%pageRow==0?this.count/pageRow:this.count/pageRow+1;
        this.lastPage = this.pageCount;
        this.prevPage = this.curPage==this.firstPage?this.firstPage:this.curPage-1;
        this.nextPage = this.curPage==this.lastPage?this.lastPage:this.curPage+1;
        if(this.pageCount<10){
            this.beginNav=this.firstPage;
            this.endNav=this.lastPage;
        }else{
            if(curPage<=6){
                this.beginNav=this.firstPage;
                this.endNav=10;
            }else if(curPage+4>=this.lastPage){
                this.beginNav=this.lastPage-9;
                this.endNav=this.lastPage;
            }else{
                this.beginNav=curPage-5;
                this.endNav=curPage+4;
            }
        }

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageRow() {
        return pageRow;
    }

    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getBeginNav() {
        return beginNav;
    }

    public void setBeginNav(int beginNav) {
        this.beginNav = beginNav;
    }

    public int getEndNav() {
        return endNav;
    }

    public void setEndNav(int endNav) {
        this.endNav = endNav;
    }
}
