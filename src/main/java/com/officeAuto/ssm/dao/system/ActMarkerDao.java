package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Actmarker;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActMarkerDao extends BaseDao<Actmarker> {

    List<Actmarker> getActivityMarker(@Param("actid")Integer actid, @Param("size")Integer size);

}
