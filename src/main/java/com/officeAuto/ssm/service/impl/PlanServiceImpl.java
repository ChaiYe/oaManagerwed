package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.PlanDao;
import com.officeAuto.ssm.model.Plan;
import com.officeAuto.ssm.model.PlanQueryModel;
import com.officeAuto.ssm.service.PlanService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PlanServiceImpl implements PlanService{


    @Autowired
    private PlanDao planDao;

    @Override
    public Plan findById(Serializable id)  throws Exception{

        Plan plan=planDao.findById(id);
        if(plan==null)
        {
            //throw  new PlanException("该用户不存在");
        }

        return plan;
    }

    @Override
    public List<Plan> findByPage(PageBean<Plan> planPageBean) throws  Exception{
        return planDao.findByPage(planPageBean);
    }

    @Override
    public int insert(Plan plan) throws  Exception {
        return planDao.insert(plan);
    }

    @Override
    public int update(Plan plan) throws  Exception {
        return planDao.update(plan);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return planDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return planDao.delete(ids);
    }

    @Override
    public List<Plan> findAll() throws  Exception {
        return planDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return planDao.findCount();
    }

    @Override
    public List<Plan> findByPageQuery(PageBean<PlanQueryModel> pageBean) throws  Exception {
        return null;
        //return planDao.findByPageQuery(pageBean);
    }
}
