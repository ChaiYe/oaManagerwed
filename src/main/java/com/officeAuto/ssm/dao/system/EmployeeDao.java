package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;

import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends BaseDao<Employee> {

    Employee findByActAndPwd(@Param("account")String account, @Param("password")String password);

    Employee findByUuid(@Param("uuid")int uuid);

    Integer findByPhone(String phone);

    void add(EmployeeAndInfo employeeAndInfo);
}
