package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.ActivityDao;
import com.officeAuto.ssm.model.Activity;
import com.officeAuto.ssm.model.ActivityQueryModel;
import com.officeAuto.ssm.service.ActivityService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService{


    @Autowired
    private ActivityDao ActivityDao;

    @Override
    public Activity findById(Serializable id)  throws Exception{

        Activity Activity=ActivityDao.findById(id);
        if(Activity==null)
        {
           // throw  new ActivityException("该用户不存在");
        }

        return Activity;
    }

    @Override
    public List<Activity> findByPage(PageBean<Activity> ActivityPageBean) throws  Exception{
        return ActivityDao.findByPage(ActivityPageBean);
    }

    @Override
    public int insert(Activity Activity) throws  Exception {
        return ActivityDao.insert(Activity);
    }

    @Override
    public int update(Activity Activity) throws  Exception {
        return ActivityDao.update(Activity);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return ActivityDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return ActivityDao.delete(ids);
    }

    @Override
    public List<Activity> findAll() throws  Exception {
        return ActivityDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return ActivityDao.findCount();
    }

    @Override
    public List<Activity> findByPageQuery(PageBean<ActivityQueryModel> pageBean) throws  Exception {
        //return ActivityDao.findByPageQuery(pageBean);
        return null;
    }
}
