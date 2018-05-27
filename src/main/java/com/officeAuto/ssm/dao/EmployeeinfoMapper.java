package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.model.Employeeinfo;
import com.officeAuto.ssm.model.EmployeeinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeinfoMapper {
    int countByExample(EmployeeinfoExample example);

    int deleteByExample(EmployeeinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employeeinfo record);

    int insertSelective(Employeeinfo record);

    List<Employeeinfo> selectByExample(EmployeeinfoExample example);

    Employeeinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employeeinfo record, @Param("example") EmployeeinfoExample example);

    int updateByExample(@Param("record") Employeeinfo record, @Param("example") EmployeeinfoExample example);

    int updateByPrimaryKeySelective(Employeeinfo record);

    int updateByPrimaryKey(Employeeinfo record);
}