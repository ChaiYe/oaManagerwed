package com.officeAuto.ssm.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean <T>{

    /**当前页*/
    private Integer currentPage=1;
    /**每也显示条数*/
    private Integer pageSize=10;
    /**总记录数*/
    private Integer totalCount;
    /**总页数*/
    private Integer totalPage;
    /**分页数据*/
    private List<T> datas;

    /**是否有上一页*/
    private boolean isHavePrePage = false;
    /**是否有下一页*/
    private boolean isHaveNextPage = false;


    /**
     * 条件map
     */
    private Map<String,Object> paraMap = new HashMap<String, Object>();

    public Map<String, Object> getParaMap() {
        return paraMap;
    }

    public void setParaMap(Map<String, Object> paraMap) {
        this.paraMap = paraMap;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        //通过总记录数和每也显示条数计算总页数
        this.totalPage = this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public boolean isHavePrePage() {
        return isHavePrePage;
    }

    public void setHavePrePage(boolean havePrePage) {
        isHavePrePage = havePrePage;
    }

    public boolean isHaveNextPage() {
        return isHaveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        isHaveNextPage = haveNextPage;
    }

}
