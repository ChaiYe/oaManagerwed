package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Announce;

import com.officeAuto.ssm.model.AnnounceQueryModel;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnounceDao extends BaseDao<Announce> {

    public List<Announce> findByPageQuery(PageBean<AnnounceQueryModel> pageBean);

}
