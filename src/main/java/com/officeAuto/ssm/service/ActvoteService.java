package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Actvote;
import com.officeAuto.ssm.model.ActvoteQueryModel;
import com.officeAuto.ssm.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface ActvoteService {

    /**
     * 根据唯一主键查找实例
     * @param id
     * @return
     */
    public Actvote findById(Serializable id) throws  Exception;

    /**
     * 分页查询实例
     * @param ActvotePageBean
     * @return
     */
    public List<Actvote> findByPage(PageBean<Actvote> ActvotePageBean) throws  Exception ;

    /**
     * 插入实例
     * @param Actvote
     * @return
     */
    public int insert(Actvote Actvote) throws  Exception;

    /**
     * 根据单例更新实例
     * @param Actvote
     * @return
     */
    public int update(Actvote Actvote) throws  Exception;

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
    public List<Actvote> findAll() throws  Exception;

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
    public List<Actvote> findByPageQuery(PageBean<ActvoteQueryModel> pageBean) throws Exception;


    /**
     * 根据投票活动的编号和用户ID判断用户是否已经投过票
     * @param voteId 投票活动的编号
     * @param empId 用户ID
     * @return
     */
    public Integer checkVoted(Integer voteId,Integer empId);

}
