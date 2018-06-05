package com.officeAuto.ssm.service.impl;

import com.officeAuto.ssm.dao.system.EmployeeAndInfoDao;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.model.EmployeeInfo;
import com.officeAuto.ssm.service.EmpAndInfoService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmpAndInfoServiceImpl implements EmpAndInfoService {


    @Autowired
    private EmployeeAndInfoDao employeeAndInfoDao;

    @Override
    public EmployeeAndInfo findById(Serializable id)  throws Exception{

        EmployeeAndInfo employeeAndInfo=employeeAndInfoDao.findById(id);
        if(employeeAndInfo==null)
        {
            //throw  new EmployeeAndInfoException("该用户不存在");
        }

        return employeeAndInfo;
    }

    @Override
    public List<EmployeeAndInfo> findByPage(PageBean<EmployeeAndInfo> employeeAndInfoPageBean) throws  Exception{
        return employeeAndInfoDao.findByPage(employeeAndInfoPageBean);
    }

    @Override
    public int insert(EmployeeAndInfo employeeAndInfo) throws  Exception {
        return employeeAndInfoDao.insert(employeeAndInfo);
    }

    @Override
    public int update(EmployeeAndInfo employeeAndInfo) throws  Exception {
        return employeeAndInfoDao.update(employeeAndInfo);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return employeeAndInfoDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return employeeAndInfoDao.delete(ids);
    }

    @Override
    public List<EmployeeAndInfo> findAll() throws  Exception {
        return employeeAndInfoDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return employeeAndInfoDao.findCount();
    }

    @Override
    public EmployeeAndInfo login(String account, String password) {
        return employeeAndInfoDao.login(account, password);
    }

    @Override
    public EmployeeAndInfo getInfo(int uuid) {
        return employeeAndInfoDao.getInfo(uuid);
    }

    @Override
    public void updateImg(int uuid, String imgPath) {
        employeeAndInfoDao.updateImg(uuid, imgPath);
    }

    @Override
    public int updateInfoSelective(EmployeeInfo record) {
        if(record.getId() == null)
            return 0;
        return employeeAndInfoDao.updateInfoSelective(record);
    }

}
