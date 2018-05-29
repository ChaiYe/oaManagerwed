package com.officeAuto.ssm.dao.useless;

import com.officeAuto.ssm.model.Actoption;
import com.officeAuto.ssm.model.ActoptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActoptionMapper {
    int countByExample(ActoptionExample example);

    int deleteByExample(ActoptionExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Actoption record);

    int insertSelective(Actoption record);

    List<Actoption> selectByExample(ActoptionExample example);

    Actoption selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Actoption record, @Param("example") ActoptionExample example);

    int updateByExample(@Param("record") Actoption record, @Param("example") ActoptionExample example);

    int updateByPrimaryKeySelective(Actoption record);

    int updateByPrimaryKey(Actoption record);
}