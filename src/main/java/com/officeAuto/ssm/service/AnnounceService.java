package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Announce;
import com.officeAuto.ssm.model.AnnounceQueryModel;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface AnnounceService {

    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public Announce findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param announcePageBean
     * @return
     */
    public List<Announce> findByPage(PageBean<Announce> announcePageBean) throws  Exception ;

    /**
     * 插入实例
     * @param announce
     * @return
     */
    public int insert(Announce announce) throws  Exception;

    /**
     * 根据单例更新实例
     * @param announce
     * @return
     */
    public int update(Announce announce) throws  Exception;

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
    public List<Announce> findAll() throws  Exception;

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
    public List<Announce> findByPageQuery(PageBean<AnnounceQueryModel> pageBean) throws Exception;

    /**
     * 查找最近的公告
     * @param deptName 部门名称
     * @param size 查找大小
     * @return
     */
    List<Announce> getRecentAnnounce(String deptName, int size);
}
