package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Actvote;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActvoteDao extends BaseDao<Actvote> {
    Integer checkVoted(@Param("voteId")Integer voteId, @Param("empId")Integer empId);
}
