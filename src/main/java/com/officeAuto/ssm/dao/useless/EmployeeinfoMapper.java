package com.officeAuto.ssm.dao.useless;

import com.officeAuto.ssm.model.EmployeeInfo;
import com.officeAuto.ssm.model.EmployeeinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeinfoMapper {
    int countByExample(EmployeeinfoExample example);

    int deleteByExample(EmployeeinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    List<EmployeeInfo> selectByExample(EmployeeinfoExample example);

    EmployeeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeInfo record, @Param("example") EmployeeinfoExample example);

    int updateByExample(@Param("record") EmployeeInfo record, @Param("example") EmployeeinfoExample example);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);
}