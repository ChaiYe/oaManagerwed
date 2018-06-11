package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.ActFileAssociate;
import com.officeAuto.ssm.model.Actfile;
import com.officeAuto.ssm.model.ActfileQueryModel;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface ActfileService {

    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public Actfile findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param ActfilePageBean
     * @return
     */
    public List<Actfile> findByPage(PageBean<Actfile> ActfilePageBean) throws  Exception ;

    /**
     * 插入实例
     * @param Actfile
     * @return
     */
    public int insert(Actfile Actfile) throws  Exception;

    /**
     * 根据单例更新实例
     * @param Actfile
     * @return
     */
    public int update(Actfile Actfile) throws  Exception;

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
    public List<Actfile> findAll() throws  Exception;

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
    public List<Actfile> findByPageQuery(PageBean<ActfileQueryModel> pageBean) throws Exception;

    /**
     * 取得活动所有文件
     * @param actid 活动id，不可空
     * @param size 不为空时，查询size条，空时查询所有
     * @return
     */
    List<ActFileAssociate> getActFileByAct(Integer actid, Integer size) throws Exception;
}
