package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.DeptDao;
import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.model.DeptQueryModel;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptDao deptDao;


    @Override
    public Dept findById(Serializable id)  throws Exception{

        Dept dept=deptDao.findById(id);
        if(dept==null)
        {
            //throw  new DeptException("该用户不存在");
        }

        return dept;
    }

    @Override
    public List<Dept> findByPage(PageBean<Dept> deptPageBean) throws  Exception{
        return deptDao.findByPage(deptPageBean);
    }

    @Override
    public int insert(Dept dept) throws  Exception {
        return deptDao.insert(dept);
    }

    @Override
    public int update(Dept dept) throws  Exception {
        return deptDao.update(dept);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return deptDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return deptDao.delete(ids);
    }

    @Override
    public List<Dept> findAll() throws  Exception {
        return deptDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return deptDao.findCount();
    }

    @Override
    public List<Dept> findByPageQuery(PageBean<DeptQueryModel> pageBean) throws  Exception {
        return null;
        //return deptDao.findByPageQuery(pageBean);
    }
    


}
