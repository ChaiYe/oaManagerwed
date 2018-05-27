package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.JobDao;
import com.officeAuto.ssm.model.Job;
import com.officeAuto.ssm.model.JobQueryModel;
import com.officeAuto.ssm.service.JobService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobService{


    @Autowired
    private JobDao jobDao;

    @Override
    public Job findById(Serializable id)  throws Exception{

        Job job=jobDao.findById(id);
        if(job==null)
        {
            //throw  new JobException("该用户不存在");
        }

        return job;
    }

    @Override
    public List<Job> findByPage(PageBean<Job> jobPageBean) throws  Exception{
        return jobDao.findByPage(jobPageBean);
    }

    @Override
    public int insert(Job job) throws  Exception {
        return jobDao.insert(job);
    }

    @Override
    public int update(Job job) throws  Exception {
        return jobDao.update(job);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return jobDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return jobDao.delete(ids);
    }

    @Override
    public List<Job> findAll() throws  Exception {
        return jobDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return jobDao.findCount();
    }

    @Override
    public List<Job> findByPageQuery(PageBean<JobQueryModel> pageBean) throws  Exception {
        return null;
        //return jobDao.findByPageQuery(pageBean);
    }
}
