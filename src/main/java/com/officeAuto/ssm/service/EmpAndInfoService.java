package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.model.EmployeeInfo;
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

    /**
     * 更新employeeinfo 表
     * EmployeeInfo 实体必须设置id字段
     * 某项信息没修改时不更新
     * @param record EmployeeInfo 实体
     * @return 影响的记录数
     */
    int updateInfoSelective(EmployeeInfo record);

    /**
     * 判断账号是否唯一
     * @param account 账号
     * @return 唯一时为真
     */
    boolean accountUnique(String account);

    /**
     * 更新账号
     * @param uuid 主码
     * @param account 新账号
     */
    void updateAccount(int uuid, String account);

    /**
     * 更新密码
     * @param uuid 主码
     * @param password 新密码
     */
    void updatePassword(int uuid, String password) ;

    /**
     * 忘记密码
     * @param phone 绑定的手机号
     * @param password 新密码
     */
    void forgetPassword(String phone, String password);

    void add(EmployeeInfo employeeInfo);
}
