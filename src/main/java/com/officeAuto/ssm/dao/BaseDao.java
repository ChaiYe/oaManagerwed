package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.utils.PageBean;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    /**带条件分页查询，条件可以为null，既没有条件；返回list对象集合*/
    public List<T> findByPage(PageBean<T> pageBean);

    /**只查询一个，常用于修改*/
    public T findById(@Param("id") Serializable id);

    /**
     * 插入，用实体作为参数
     * @param entity
     */
    public int insert(T entity);

    /**
     * 修改，用实体作为参数
     * @param entity
     */
    public int update(T entity);

    /**
     * 按id删除，删除一条；支持整数型和字符串类型ID
     * @param id
     */
    public int deleteById(Serializable id);

    /**
     * 批量删除；支持整数型和字符串类型ID
     * @param ids
     */
    public int delete(@Param("ids")Serializable[] ids);

    /**
     * 查询全部
     *
     */
    public List<T> findAll();

    /**
     * 查询记录数
     * @param
     */
    public int findCount();

}
