package com.officeAuto.ssm.model;

import java.util.List;

public class EmployeeAndInfo extends Employee {

    private EmployeeInfo employeeInfo;
    private List<JobQueryModel> jobs;

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public List<JobQueryModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobQueryModel> jobs) {
        this.jobs = jobs;
    }
}
