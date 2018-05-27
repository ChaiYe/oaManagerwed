package com.officeAuto.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("management")
public class ManagementController {

    @RequestMapping("/management")
    public String login(HttpServletRequest request)
    {
        return "management";
    }

    @RequestMapping("/homepage")
    public String homePage(HttpServletRequest request)
    {
        return "leftBox/homepage";
    }

    @RequestMapping("addEmplyee")
    public String addEmployee(HttpServletRequest request){return  "leftBox/add/addEmployeeInfo";}
}
