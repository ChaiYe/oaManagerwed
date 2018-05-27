package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Actoption;
import com.officeAuto.ssm.model.ActoptionQueryModel;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface ActoptionService {

    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public Actoption findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param ActoptionPageBean
     * @return
     */
    public List<Actoption> findByPage(PageBean<Actoption> ActoptionPageBean) throws  Exception ;

    /**
     * 插入实例
     * @param Actoption
     * @return
     */
    public int insert(Actoption Actoption) throws  Exception;

    /**
     * 根据单例更新实例
     * @param Actoption
     * @return
     */
    public int update(Actoption Actoption) throws  Exception;

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
    public List<Actoption> findAll() throws  Exception;

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
    public List<Actoption> findByPageQuery(PageBean<ActoptionQueryModel> pageBean) throws Exception;
}
