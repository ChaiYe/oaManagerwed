package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.model.Plan;
import com.officeAuto.ssm.model.PlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanMapper {
    int countByExample(PlanExample example);

    int deleteByExample(PlanExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Plan record);

    int insertSelective(Plan record);

    List<Plan> selectByExampleWithBLOBs(PlanExample example);

    List<Plan> selectByExample(PlanExample example);

    Plan selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Plan record, @Param("example") PlanExample example);

    int updateByExampleWithBLOBs(@Param("record") Plan record, @Param("example") PlanExample example);

    int updateByExample(@Param("record") Plan record, @Param("example") PlanExample example);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKeyWithBLOBs(Plan record);

    int updateByPrimaryKey(Plan record);
}