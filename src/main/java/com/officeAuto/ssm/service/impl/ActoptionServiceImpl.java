package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.ActoptionDao;

import com.officeAuto.ssm.model.Actoption;
import com.officeAuto.ssm.model.ActoptionQueryModel;
import com.officeAuto.ssm.service.ActoptionService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActoptionServiceImpl implements ActoptionService{


    @Autowired
    private ActoptionDao ActoptionDao;

    @Override
    public Actoption findById(Serializable id)  throws Exception{

        Actoption Actoption=ActoptionDao.findById(id);
        if(Actoption==null)
        {
            //throw  new ActoptionException("该用户不存在");
        }

        return Actoption;
    }

    @Override
    public List<Actoption> findByPage(PageBean<Actoption> ActoptionPageBean) throws  Exception{
        return ActoptionDao.findByPage(ActoptionPageBean);
    }

    @Override
    public int insert(Actoption Actoption) throws  Exception {
        return ActoptionDao.insert(Actoption);
    }

    @Override
    public int update(Actoption Actoption) throws  Exception {
        return ActoptionDao.update(Actoption);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return ActoptionDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return ActoptionDao.delete(ids);
    }

    @Override
    public List<Actoption> findAll() throws  Exception {
        return ActoptionDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return ActoptionDao.findCount();
    }

    @Override
    public List<Actoption> findByPageQuery(PageBean<ActoptionQueryModel> pageBean) throws  Exception {
       return  null;
        //return ActoptionDao.findByPageQuery(pageBean);
    }
}
