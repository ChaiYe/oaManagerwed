package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.ActfileDao;
import com.officeAuto.ssm.model.ActFileAssociate;
import com.officeAuto.ssm.model.Actfile;
import com.officeAuto.ssm.model.ActfileQueryModel;
import com.officeAuto.ssm.service.ActfileService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActfileServiceImpl implements ActfileService{


    @Autowired
    private ActfileDao actfileDao;

    @Override
    public Actfile findById(Serializable id)  throws Exception{

        Actfile Actfile= actfileDao.findById(id);
        if(Actfile==null)
        {
           // throw  new ActfileException("该用户不存在");
        }

        return Actfile;
    }

    @Override
    public List<Actfile> findByPage(PageBean<Actfile> ActfilePageBean) throws  Exception{
        return actfileDao.findByPage(ActfilePageBean);
    }

    @Override
    public int insert(Actfile Actfile) throws  Exception {
        return actfileDao.insert(Actfile);
    }

    @Override
    public int update(Actfile Actfile) throws  Exception {
        return actfileDao.update(Actfile);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return actfileDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return actfileDao.delete(ids);
    }

    @Override
    public List<Actfile> findAll() throws  Exception {
        return actfileDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return actfileDao.findCount();
    }

    @Override
    public List<Actfile> findByPageQuery(PageBean<ActfileQueryModel> pageBean) throws  Exception {
        //return actfileDao.findByPageQuery(pageBean);
        return null;
    }

    @Override
    public List<ActFileAssociate> getActFileByAct(Integer actid, Integer size) throws Exception {
        if(actid == null)
            throw new Exception();
        return actfileDao.getActFileByAct(actid, size);
    }
}
