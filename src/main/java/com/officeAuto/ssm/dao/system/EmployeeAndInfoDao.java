package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import org.apache.ibatis.annotations.Param;

public interface EmployeeAndInfoDao extends BaseDao<EmployeeAndInfo> {

    EmployeeAndInfo findByID(@Param("uuid")int uuid);

    EmployeeAndInfo login(@Param("account")String account, @Param("password")String password);

    EmployeeAndInfo getInfo(@Param("uuid")int uuid);

}
