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
    private ActivityDao activityDao;

    @Override
    public Activity findById(Serializable id)  throws Exception{

        Activity Activity = activityDao.findById(id);
        if(Activity==null)
        {
           // throw  new ActivityException("该用户不存在");
        }

        return Activity;
    }

    @Override
    public List<Activity> findByPage(PageBean<Activity> ActivityPageBean) throws  Exception{
        return activityDao.findByPage(ActivityPageBean);
    }

    @Override
    public int insert(Activity Activity) throws  Exception {
        return activityDao.insert(Activity);
    }

    @Override
    public int update(Activity Activity) throws  Exception {
        return activityDao.update(Activity);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return activityDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return activityDao.delete(ids);
    }

    @Override
    public List<Activity> findAll() throws  Exception {

        return activityDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return activityDao.findCount();
    }

    @Override
    public List<Activity> findByPageQuery(PageBean<ActivityQueryModel> pageBean) throws  Exception {
        //return activityDao.findByPageQuery(pageBean);
        return null;
    }

    @Override
    public List<ActivityQueryModel> getRecentActivity(int deptid, int size) {
        return activityDao.getRecentActivity(deptid, size);
    }

    @Override
    public List<ActivityQueryModel> getRecentActDept(Integer deptid, Integer size) throws Exception{
        if(deptid == null)
            throw new Exception();
        return activityDao.getRecentActDept(deptid, size);
    }

    @Override
    public ActivityQueryModel getById(Integer actid) throws Exception{
        if(actid == null)
            throw new Exception();
        return activityDao.getById(actid);
    }

}
