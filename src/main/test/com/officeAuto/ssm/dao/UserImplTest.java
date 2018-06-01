package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.dao.system.*;
import com.officeAuto.ssm.dao.useless.ActoptionMapper;
import com.officeAuto.ssm.dao.useless.EmployeeMapper;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.utils.PageBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;

public class UserImplTest {

    private ApplicationContext applicationContext;
    @Before
    public void setUp()  {
        //创建spring容器

        applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");


    }

    /*@Test
    public void testFindUserById()  {

        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User user = userDao.selectUserById((long) 1);
        System.out.println(user);
    }*/

    @Test
    public void testActivityDao(){
//        ActivityDao activityDao=(ActivityDao)applicationContext.getBean("activityDao");
//        PageBean<Activity> deptPageBean=new PageBean<Activity>();
//        List<Activity> activities=activityDao.findByPage(deptPageBean);
//        for(Activity activity:activities){
//            System.out.println(activity);
//        }
        EmployeeAndInfoDao dao=(EmployeeAndInfoDao) applicationContext.getBean("employeeAndInfoDao");

        EmployeeAndInfo employeeAndInfo = dao.login("A002", "123");
        System.out.println(employeeAndInfo.getJobs().size()+"chenggogn");
    }

    @Test
    public void testFindDeptDao(){
        DeptDao deptDao=(DeptDao) applicationContext.getBean("deptDao");
        PageBean<Dept> deptPageBean=new PageBean<Dept>();
        List<Dept> depts=deptDao.findByPage(deptPageBean);

        for(Dept dept:depts){
            System.out.println(dept);
        }
    }

    @Test
    public void testAnnounceDao(){
        AnnounceDao announceDao=(AnnounceDao) applicationContext.getBean("announceDao");
        /*测试分页*/
        /*PageBean<Announce> announcePageBean=new PageBean<Announce>();
        List<Announce> announces=announceDao.findByPage(announcePageBean);
        for(Announce announce:announces){
            System.out.println(announce);
        }
        System.out.println("part1");*/
        /*测试查询单例*/
       /* Announce announce=announceDao.findById(1);
        System.out.println(announce);
        System.out.println("part2");*/
        /*测试插入单例，因为外键存在所以有可能插入失败*/
        /*Announce announce1=new Announce();
        announce1.setDescript("妞妞修改信息");
        announce1.setDept(1);
        announce1.setEmployee(1);
        announceDao.insert(announce1);*/
        /*测试批量删除*/
        /*Integer[] ids={6,7};
        announceDao.delete(ids);*/
        /*测试更新*/
        /*Integer id=announceDao.update(announce1);
        System.out.println("id="+id);
        Announce announce2=announceDao.findById(id);
        System.out.println(announce2);
        System.out.println("part3");*/
        /*测试条件查询*/
        AnnounceQueryModel announceQueryModel=new AnnounceQueryModel();
        announceQueryModel.setQueryAcount1(2);
        announceQueryModel.setQueryAcount2(9);
        PageBean<AnnounceQueryModel> pageBeanQuery= new PageBean<AnnounceQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("announceQuery",announceQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Announce> announcequerys= announceDao.findByPageQuery(pageBeanQuery);

        for(Announce announce:announcequerys){
            System.out.println(announce);
        }
    }

    @Test
    public  void  testActoptionmapper(){
        ActoptionMapper actoptionMapper=(ActoptionMapper) applicationContext.getBean("actoptionMapper");
        Actoption actoption= actoptionMapper.selectByPrimaryKey(1);
        System.out.println(actoption);
    }

    /*@Test
    public  void  testDeptmapper(){
        DeptMapper deptMapper=(DeptMapper) applicationContext.getBean("deptMapper");
                Dept dept=deptMapper.selectByPrimaryKey(1);
        System.out.println(dept);
    }*/

    @Test
    public  void  testUserNameAndPwd(){
        EmployeeMapper employeeMapper=(EmployeeMapper) applicationContext.getBean("employeeMapper");
        Employee employee=employeeMapper.login("A001","123");
        System.out.println(employee);
    }

    /*@Test
    public void testEmpList(){
        EmployeeMapper employeeMapper=(EmployeeMapper) applicationContext.getBean("employeeMapper");
        List<EmployeeAndInfo> employeeAndInfos=employeeMapper.findEmpInfoList();
        System.out.println(employeeAndInfos.get(1).getName());
    }*/

   /* @Test
    public void testDeleteEmpById(){
        EmployeeMapper employeeMapper=(EmployeeMapper) applicationContext.getBean("employeeMapper");
        employeeMapper.deleteByPrimaryKey(3);
        System.out.println("success");
    }*/

    /*@Test
    public void testDeptListByPage(){
        DeptMapper deptMapper=(DeptMapper) applicationContext.getBean("deptMapper");
        List<Dept> depts= deptMapper.findDeptListByPage(1,2);
        for (Dept dept:depts
             ) {
            System.out.println(dept);
        }
    }*/
}
