package com.officeAuto.ssm.model;

import java.util.Date;

public class Plan {
    private Integer uuid;

    private Date createtime;

    private Date begintime;

    private Date endtime;

    private String state;

    private Integer employee;

    private String descipt;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public String getDescipt() {
        return descipt;
    }

    public void setDescipt(String descipt) {
        this.descipt = descipt == null ? null : descipt.trim();
    }
}