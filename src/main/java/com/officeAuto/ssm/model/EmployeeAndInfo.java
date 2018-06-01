package com.officeAuto.ssm.model;

import java.util.List;

public class EmployeeAndInfo extends Employee {

    private EmployeeInfo employeeInfo;
    private List<Job> jobs;

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
