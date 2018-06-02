package com.officeAuto.ssm.model;

public class JobQueryModel extends Job implements BaseQueryModel {

    private Dept depart;

    public Dept getDepart() {
        return depart;
    }

    public void setDepart(Dept depart) {
        this.depart = depart;
    }
}
