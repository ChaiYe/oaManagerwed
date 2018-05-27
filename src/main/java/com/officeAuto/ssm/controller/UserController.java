package com.officeAuto.ssm.controller;

import com.officeAuto.ssm.model.User;
import com.officeAuto.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request)
    {
        log.info("登录");
        return "login";
    }


    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "test";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, Long userId){
        log.info("删除用户信息");

        userService.delete(userId);

        return "redirect:/user/showUser.action";
    }

    /*
    @RequestMapping("/testJson")
    public @ResponseBody
    String testJson(HttpServletRequest request, Model model){

        Dept dept=deptService.getDeptById(1);

        String deptJson= JSON.toJSONString(dept);

        System.out.println(deptJson);

        return  deptJson;
    }*/

    /*@RequestMapping("/showAllDept")
    public String showDept(HttpServletRequest request, Model model){

        Dept dept=deptService.getDeptById(1);

        model.addAttribute(dept);

        return "test";
    }*/
}
