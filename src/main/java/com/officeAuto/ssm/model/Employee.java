package com.officeAuto.ssm.model;

import java.util.Date;

public class Employee {
    private Integer uuid;

    private String account;

    private String password;

    private String state;

    private Date createtime;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "uuid=" + uuid +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}