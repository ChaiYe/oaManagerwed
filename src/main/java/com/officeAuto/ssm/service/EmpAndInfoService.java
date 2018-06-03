package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface EmpAndInfoService {

    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public EmployeeAndInfo findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param employeeAndInfoPageBean
     * @return
     */
    public List<EmployeeAndInfo> findByPage(PageBean<EmployeeAndInfo> employeeAndInfoPageBean) throws  Exception ;

    /**
     * 插入实例
     * @param employeeAndInfo
     * @return
     */
    public int insert(EmployeeAndInfo employeeAndInfo) throws  Exception;

    /**
     * 根据单例更新实例
     * @param employeeAndInfo
     * @return
     */
    public int update(EmployeeAndInfo employeeAndInfo) throws  Exception;

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
    public List<EmployeeAndInfo> findAll() throws  Exception;

    /**
     * 查找记录数量
     * @return
     */
    public int findCount() throws  Exception;

    /**
     * 登录检验
     * @param account 账号
     * @param password 密码
     * @return 员工实体+员工信息+岗位信息
     */
    EmployeeAndInfo login(String account, String password);

    /**
     * 根据主键查信息
     * @param uuid
     * @return
     */
    EmployeeAndInfo getInfo(int uuid);

    /**
     * 更新头像
     * @param uuid 实体主键
     * @param imgPath 图片路径
     */
    void updateImg(int uuid, String imgPath);
}
