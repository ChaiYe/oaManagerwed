package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.dao.system.*;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.utils.PageBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class UserImplTest {

    private ApplicationContext applicationContext;
    @Before
    public void setUp()  {
        //创建spring容器
        applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    }

    @Test
    public void test(){
        ActfileDao dao = (ActfileDao) applicationContext.getBean("actfileDao");
        List<ActFileAssociate> actFileAssociates = dao.getActFileByAct(1, null);
        System.out.println();
    }

    @Test
    public void testActivityDao(){
//        ActivityDao activityDao=(ActivityDao)applicationContext.getBean("activityDao");
//        PageBean<Activity> deptPageBean=new PageBean<Activity>();
//        List<Activity> activities=activityDao.findByPage(deptPageBean);
//        for(Activity activity:activities){
//            System.out.println(activity);
//        }
        ActivityDao dao=(ActivityDao) applicationContext.getBean("activityDao");

        ActivityQueryModel activityQueryModel = dao.getById(7);
        System.out.println();
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
        List<AnnounceDpet> announceDpetss = announceDao.findAllWithDept(1,1);
        for(AnnounceDpet announceDpet:announceDpetss){
            System.out.println(announceDpet.getDepart().getName());
        }
    }

}
