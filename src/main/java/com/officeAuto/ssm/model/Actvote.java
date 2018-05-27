package com.officeAuto.ssm.model;

import java.util.Date;

public class Actvote {
    private Integer uuid;

    private Date votetime;

    private Integer actoption;

    private Integer employee;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Date getVotetime() {
        return votetime;
    }

    public void setVotetime(Date votetime) {
        this.votetime = votetime;
    }

    public Integer getActoption() {
        return actoption;
    }

    public void setActoption(Integer actoption) {
        this.actoption = actoption;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }
}