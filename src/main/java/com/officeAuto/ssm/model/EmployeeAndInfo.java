package com.officeAuto.ssm.model;

public class EmployeeAndInfo extends Employee {
    private  Employeeinfo employeeinfo;

    public Employeeinfo getEmployeeinfo() {
        return employeeinfo;
    }

    public void setEmployeeinfo(Employeeinfo employeeinfo) {
        this.employeeinfo = employeeinfo;
    }
}
