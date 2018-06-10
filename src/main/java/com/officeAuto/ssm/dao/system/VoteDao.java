package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Vote;
import com.officeAuto.ssm.model.VoteQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteDao extends BaseDao<Vote> {

    List<VoteQueryModel> getByAct(@Param("actid")Integer actid);
}
