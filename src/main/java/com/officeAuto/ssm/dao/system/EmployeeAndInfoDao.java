package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import org.apache.ibatis.annotations.Param;

public interface EmployeeAndInfoDao extends BaseDao<EmployeeAndInfo> {

    /**
     * 已弃用
     * 登录验证，以及获取信息
     *
     * @param account
     * @param password
     * @return
     */
    EmployeeAndInfo login(@Param("account")String account, @Param("password")String password);

    /**
     * 获取信息
     * @param uuid 员工实体uuid
     * @return
     */
    EmployeeAndInfo getInfo(@Param("uuid")int uuid);

    /**
     * 更新头像
     * @param uuid 员工主键
     * @param imgPath 文件路径
     */
    void updateImg(@Param("uuid")int uuid, @Param("imgPath")String imgPath);

}
