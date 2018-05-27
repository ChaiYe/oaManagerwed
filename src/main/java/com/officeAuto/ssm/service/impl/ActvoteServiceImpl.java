package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.ActvoteDao;
import com.officeAuto.ssm.model.Actvote;
import com.officeAuto.ssm.model.ActvoteQueryModel;
import com.officeAuto.ssm.service.ActvoteService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActvoteServiceImpl implements ActvoteService{


    @Autowired
    private ActvoteDao actvoteDao;

    @Override
    public Actvote findById(Serializable id)  throws Exception{

        Actvote actvote=actvoteDao.findById(id);
        if(actvote==null)
        {
           // throw  new ActvoteException("该用户不存在");
        }

        return actvote;
    }

    @Override
    public List<Actvote> findByPage(PageBean<Actvote> actvotePageBean) throws  Exception{
        return actvoteDao.findByPage(actvotePageBean);
    }

    @Override
    public int insert(Actvote actvote) throws  Exception {
        return actvoteDao.insert(actvote);
    }

    @Override
    public int update(Actvote actvote) throws  Exception {
        return actvoteDao.update(actvote);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return actvoteDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return actvoteDao.delete(ids);
    }

    @Override
    public List<Actvote> findAll() throws  Exception {
        return actvoteDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return actvoteDao.findCount();
    }

    @Override
    public List<Actvote> findByPageQuery(PageBean<ActvoteQueryModel> pageBean) throws  Exception {
        return null;
        //return actvoteDao.findByPageQuery(pageBean);
    }
}
