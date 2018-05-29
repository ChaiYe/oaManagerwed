package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDao extends BaseDao<Dept> {

    List<Dept> findByPage(PageBean<Dept> pageBean);

}
