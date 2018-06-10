package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.ActMarkerDao;
import com.officeAuto.ssm.model.Actmarker;
import com.officeAuto.ssm.service.ActMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActMarkerServiceImpl implements ActMarkerService {

    @Autowired
    private ActMarkerDao actMarkerDao;

    @Override
    public List<Actmarker> getActivityMarker(Integer actid, Integer size) throws Exception{
        if(actid == null)
            throw new Exception();
        return actMarkerDao.getActivityMarker(actid, size);
    }

    @Override
    public Integer insert(Actmarker actmarker) throws Exception {
        if(actmarker == null)
            throw new Exception();
        return actMarkerDao.insert(actmarker);
    }
}
