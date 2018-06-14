package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface EmployeeService {

    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public Employee findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param employeePageBean
     * @return
     */
    public List<Employee> findByPage(PageBean<Employee> employeePageBean) throws  Exception ;

    /**
     * 插入实例
     * @param employee
     * @return
     */
    public int insert(Employee employee) throws  Exception;

    /**
     * 根据单例更新实例
     * @param employee
     * @return
     */
    public int update(Employee employee) throws  Exception;

    /**
     * 根据唯一主键删除实例
     * @param id
     * @return
     */
    public int deleteById(Serializable id) throws  Exception;

    /**
     * 根据主键数组批量删除实例
     * @param ids
     * @return
     */
    public int delete(Serializable[] ids) throws  Exception;

    /**
     * 查找全部实例
     * @return
     */
    public List<Employee> findAll() throws  Exception;

    /**
     * 查找记录数量
     * @return
     */
    public int findCount() throws  Exception;



    /**
     * 条件查询
     * @param pageBean 带实例查询条件的pageBean
     * @return 查询列表
     */
    //public List<Employee> findByPageQuery(PageBean<EmployeeQueryModel> pageBean) throws Exception;

    /**
     * 根据用户账号与密码查询
     * @param account
     * @param password
     * @return
     */
    Employee login(String account, String password);

    /**
     * 根据用户电话查找用户
     * @param phone
     * @return
     */
    Integer  findByPhone(String phone);

    void add(EmployeeAndInfo employeeAndInfo);
}
