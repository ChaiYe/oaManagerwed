package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.model.Announce;
import com.officeAuto.ssm.model.AnnounceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceMapper {
    int countByExample(AnnounceExample example);

    int deleteByExample(AnnounceExample example);

    int deleteByPrimaryKey(Integer uuid);

    int insert(Announce record);

    int insertSelective(Announce record);

    List<Announce> selectByExampleWithBLOBs(AnnounceExample example);

    List<Announce> selectByExample(AnnounceExample example);

    Announce selectByPrimaryKey(Integer uuid);

    int updateByExampleSelective(@Param("record") Announce record, @Param("example") AnnounceExample example);

    int updateByExampleWithBLOBs(@Param("record") Announce record, @Param("example") AnnounceExample example);

    int updateByExample(@Param("record") Announce record, @Param("example") AnnounceExample example);

    int updateByPrimaryKeySelective(Announce record);

    int updateByPrimaryKeyWithBLOBs(Announce record);

    int updateByPrimaryKey(Announce record);
}