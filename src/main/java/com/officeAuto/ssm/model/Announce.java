package com.officeAuto.ssm.model;

import java.util.Date;

public class Announce {
    private Integer uuid;

    private String title;

    private Date createtime;

    private Integer dept;

    private Integer employee;

    private String descript;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    @Override
    public String toString() {
        return "Announce{" +
                "uuid=" + uuid +
                ", createtime=" + createtime +
                ", dept=" + dept +
                ", employee=" + employee +
                ", descript='" + descript + '\'' +
                '}';
    }
}