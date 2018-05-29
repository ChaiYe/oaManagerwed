package com.officeAuto.ssm.dao.useless;

import com.officeAuto.ssm.model.Actvote;
import com.officeAuto.ssm.model.ActvoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActvoteMapper {
    int countByExample(ActvoteExample example);

    int deleteByExample(ActvoteExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Actvote record);

    int insertSelective(Actvote record);

    List<Actvote> selectByExample(ActvoteExample example);

    Actvote selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Actvote record, @Param("example") ActvoteExample example);

    int updateByExample(@Param("record") Actvote record, @Param("example") ActvoteExample example);

    int updateByPrimaryKeySelective(Actvote record);

    int updateByPrimaryKey(Actvote record);
}