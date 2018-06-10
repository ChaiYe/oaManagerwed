package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Actmarker;

import java.util.List;

public interface ActMarkerService {

    /**
     * 查询活动的已有里程碑，按产生时间、主码降序
     * @param actid 不可为空，活动主码
     * @param size 不为空时限制取得的数量
     * @return
     */
    List<Actmarker> getActivityMarker(Integer actid, Integer size) throws Exception;

    Integer insert(Actmarker actmarker) throws Exception;
}
