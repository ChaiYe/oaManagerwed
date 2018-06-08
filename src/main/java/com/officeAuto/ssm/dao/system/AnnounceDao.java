package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Announce;

import com.officeAuto.ssm.model.AnnounceQueryModel;
import com.officeAuto.ssm.model.AnnounceDpet;
import com.officeAuto.ssm.utils.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnounceDao extends BaseDao<Announce> {

    public List<Announce> findByPageQuery(PageBean<AnnounceQueryModel> pageBean);

    List<Announce> getRecentAnnounce(@Param("deptName")String deptName, @Param("size")int size);

    List<AnnounceDpet> getRecentAnnounceWithDept(@Param("uuid")int uuid, @Param("size")int size);

    List<AnnounceDpet> findAllWithDept(@Param("id")Integer id,@Param("nowSize")Integer nowSize);
}
