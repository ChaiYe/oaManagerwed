package com.officeAuto.ssm.model;

public class AnnounceQueryModel extends Announce implements  BaseQueryModel {

    private Integer queryAcount1;

    private Integer queryAcount2;

    public Integer getQueryAcount1() {
        return queryAcount1;
    }

    public void setQueryAcount1(Integer queryAcount1) {
        this.queryAcount1 = queryAcount1;
    }

    public Integer getQueryAcount2() {
        return queryAcount2;
    }

    public void setQueryAcount2(Integer queryAcount2) {
        this.queryAcount2 = queryAcount2;
    }
}
