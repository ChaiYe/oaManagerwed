package com.officeAuto.ssm.service.impl;


import com.officeAuto.ssm.dao.system.EmployeeDao;
import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.service.EmployeeService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee findById(Serializable id)  throws Exception{

        Employee employee=employeeDao.findById(id);
        if(employee==null)
        {
            //throw  new EmployeeException("该用户不存在");
        }

        return employee;
    }

    @Override
    public List<Employee> findByPage(PageBean<Employee> employeePageBean) throws  Exception{
        return employeeDao.findByPage(employeePageBean);
    }

    @Override
    public int insert(Employee employee) throws  Exception {
        return employeeDao.insert(employee);
    }

    @Override
    public int update(Employee employee) throws  Exception {
        return employeeDao.update(employee);
    }

    @Override
    public int deleteById(Serializable id) throws  Exception {
        return employeeDao.deleteById(id);
    }

    @Override
    public int delete(Serializable[] ids) throws  Exception {
        return employeeDao.delete(ids);
    }

    @Override
    public List<Employee> findAll() throws  Exception {
        return employeeDao.findAll();
    }

    @Override
    public int findCount() throws  Exception {
        return employeeDao.findCount();
    }

    @Override
    public Employee login(String account, String password) {
        return employeeDao.findByActAndPwd(account,password);
    }


}
