package com.officeAuto.ssm.dao.useless;

import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.model.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {

    Employee login(@Param("account")String account,@Param("password")String password);

    List<Employee> findEmpList();

    List<EmployeeAndInfo> findEmpInfoList();

    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}