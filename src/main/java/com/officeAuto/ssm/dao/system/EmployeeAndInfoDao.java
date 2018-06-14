package com.officeAuto.ssm.dao.system;

import com.officeAuto.ssm.dao.BaseDao;
import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.model.EmployeeInfo;
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

    /**
     * 更新信息
     * EmployeeInfo 实体必须设置id字段
     * 某项信息没修改时不更新
     * @param record EmployeeInfo 实体
     * @return
     */
    int updateInfoSelective(@Param("record") EmployeeInfo record);

    /**
     * 判断账号是否唯一
     * @param account 输入的账号
     * @return
     */
    Integer accountUnique(@Param("account")String account);

    /**
     * 更新登录账号
     * @param uuid 员工主码
     * @param account 账号
     * @return
     */
    Integer updateAccount(@Param("uuid")int uuid, @Param("account")String account);

    /**
     * 更新密码
     * @param uuid 员工主键
     * @param passWord 新密码
     * @return
     */
    Integer updatePassWord(@Param("uuid")int uuid, @Param("passWord")String passWord);

    /**
     * 忘记密码
     * @param phone 绑定的手机号码
     * @param password  新密码
     */
    void forgetPassword(@Param("phone")String phone, @Param("passWord")String password) ;
}
