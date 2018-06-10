package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Activity;

import com.officeAuto.ssm.model.ActivityQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao extends BaseDao<Activity> {

    List<ActivityQueryModel> getRecentActivity(@Param("deptid")Integer deptid, @Param("size")Integer size);

    List<ActivityQueryModel> getRecentActDept(@Param("deptid")Integer deptid, @Param("size")Integer size);

    ActivityQueryModel getById(@Param("actid")Integer actid);
    
    
}
