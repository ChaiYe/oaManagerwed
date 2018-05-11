package com.officeAuto.ssm.dao;

import com.officeAuto.ssm.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserImplTest {

    private ApplicationContext applicationContext;
    @Before
    public void setUp()  {
        //创建spring容器

        applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");


    }

    @Test
    public void testFindUserById()  {

        //从spring容器中获取UserDao这个bean

        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User user = userDao.selectUserById((long) 1);
        System.out.println(user);
    }

}
