package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.AnnounceDao;
import com.officeAuto.ssm.exception.AnnounceException;
import com.officeAuto.ssm.model.Announce;
import com.officeAuto.ssm.model.AnnounceDpet;
import com.officeAuto.ssm.model.AnnounceQueryModel;
import com.officeAuto.ssm.service.AnnounceService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AnnounceServiceImpl  implements AnnounceService{


    @Autowired
    private AnnounceDao announceDao;

    @Override
    public Announce findById(Serializable id)  throws Exception{

        Announce announce=announceDao.findById(id);
        if(announce==null)
        {
            throw  new AnnounceException("该用户不存在");
        }

        return announce;
    }

    @Override
    public List<Announce> findByPage(PageBean<Announce> announcePageBean) throws  Exception{
        return announceDao.findByPage(announcePageBean);
    }

    @Override
    public int insert(Announce announce) throws  Exception {
        return announceDao.insert(announce);
    }

    @Override
    public int update(Announce announce) throws  Exception {
        return announceDao.update(announce);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return announceDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return announceDao.delete(ids);
    }

    @Override
    public List<Announce> findAll() throws  Exception {
        return announceDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return announceDao.findCount();
    }

    @Override
    public List<Announce> findByPageQuery(PageBean<AnnounceQueryModel> pageBean) throws  Exception {
        return announceDao.findByPageQuery(pageBean);
    }

    @Override
    public List<Announce> getRecentAnnounce(String deptName, int size) {
        return announceDao.getRecentAnnounce(deptName, size);
    }

    @Override
    public List<AnnounceDpet> getAnnounceDept(int dept, int size) {
        return announceDao.getRecentAnnounceWithDept(dept, size);
    }

    @Override
    public List<AnnounceDpet> findAllWithDept(int id,int nowSize) {
        return announceDao.findAllWithDept(id,nowSize);
    }
}
