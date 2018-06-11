package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.ActFileAssociate;
import com.officeAuto.ssm.model.Actfile;
import com.officeAuto.ssm.model.ActfileQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActfileDao extends BaseDao<Actfile> {

    List<ActFileAssociate> getActFileByAct(@Param("actid")Integer actid, @Param("size")Integer size);
}
