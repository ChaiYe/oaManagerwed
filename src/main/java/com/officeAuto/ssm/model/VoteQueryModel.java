package com.officeAuto.ssm.model;

import java.util.List;

public class VoteQueryModel extends Vote{

    private List<Actoption> options;

    public List<Actoption> getOptions() {
        return options;
    }

    public void setOptions(List<Actoption> options) {
        this.options = options;
    }
}
