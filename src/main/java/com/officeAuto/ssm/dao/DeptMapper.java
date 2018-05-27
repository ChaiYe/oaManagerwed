package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.model.DeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptMapper extends BaseDao<Dept>{

}