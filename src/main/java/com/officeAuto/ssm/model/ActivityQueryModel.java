package com.officeAuto.ssm.model;

import java.util.List;

public class ActivityQueryModel extends Activity implements BaseQueryModel {

    private Dept depart;
    private List<Actmarker> markers;

    public Dept getDepart() {
        return depart;
    }

    public void setDepart(Dept depart) {
        this.depart = depart;
    }

    public List<Actmarker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Actmarker> markers) {
        this.markers = markers;
    }
}
