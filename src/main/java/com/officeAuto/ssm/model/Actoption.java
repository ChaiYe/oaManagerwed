package com.officeAuto.ssm.model;

public class Actoption {
    private Integer uuid;

    private String descript;

    private Integer num;

    private Integer activity;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Actoption{" +
                "uuid=" + uuid +
                ", descript='" + descript + '\'' +
                ", num=" + num +
                ", activity=" + activity +
                '}';
    }
}