package com.officeAuto.ssm.dao.useless;

import com.officeAuto.ssm.model.Job;
import com.officeAuto.ssm.model.JobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobMapper {
    int countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExampleWithBLOBs(JobExample example);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExampleWithBLOBs(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKeyWithBLOBs(Job record);

    int updateByPrimaryKey(Job record);
}