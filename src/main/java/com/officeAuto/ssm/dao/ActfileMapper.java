package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.model.Actfile;
import com.officeAuto.ssm.model.ActfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActfileMapper {
    int countByExample(ActfileExample example);

    int deleteByExample(ActfileExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Actfile record);

    int insertSelective(Actfile record);

    List<Actfile> selectByExample(ActfileExample example);

    Actfile selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Actfile record, @Param("example") ActfileExample example);

    int updateByExample(@Param("record") Actfile record, @Param("example") ActfileExample example);

    int updateByPrimaryKeySelective(Actfile record);

    int updateByPrimaryKey(Actfile record);
}