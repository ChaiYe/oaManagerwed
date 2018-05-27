package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.model.DeptQueryModel;
import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface DeptService {

    /**
     *查询所有部门信息
     * @return 部门信息列表
     */
   // public List<Dept> findDeptList();


    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public Dept findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param deptPageBean
     * @return
     */
    public List<Dept> findByPage(PageBean<Dept> deptPageBean) throws  Exception ;

    /**
     * 插入实例
     * @param dept
     * @return
     */
    public int insert(Dept dept) throws  Exception;

    /**
     * 根据单例更新实例
     * @param dept
     * @return
     */
    public int update(Dept dept) throws  Exception;

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
    public List<Dept> findAll() throws  Exception;

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
    public List<Dept> findByPageQuery(PageBean<DeptQueryModel> pageBean) throws Exception;


}
