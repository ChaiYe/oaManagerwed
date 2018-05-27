package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;

import com.officeAuto.ssm.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends BaseDao<Employee> {

    Employee findByActAndPwd(@Param("account")String account, @Param("password")String password);
}
