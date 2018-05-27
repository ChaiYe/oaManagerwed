package com.officeAuto.ssm.model;

public class Dept {
    private Integer uuid;

    private String name;

    private String descript;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    @Override
    public String toString() {
        return "Dept{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", descript='" + descript + '\'' +
                '}';
    }
}