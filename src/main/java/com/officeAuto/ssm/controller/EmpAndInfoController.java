package com.officeAuto.ssm.controller;

import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.service.EmpAndInfoService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/empAndInfo")
public class EmpAndInfoController {

    @Autowired
    private EmpAndInfoService empAndInfoService;
    
    @RequestMapping("/getEmpAndInfoByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<EmployeeAndInfo> employeeAndInfoPageBean= new PageBean<EmployeeAndInfo>();
        if(currentPage!=null)
        {
            employeeAndInfoPageBean.setCurrentPage(currentPage);
        }
        List<EmployeeAndInfo> employeeAndInfos=empAndInfoService.findByPage(employeeAndInfoPageBean);
        employeeAndInfoPageBean.setDatas(employeeAndInfos);

        Integer totalCount =empAndInfoService.findCount();
        employeeAndInfoPageBean.setTotalCount(totalCount);

        model.addAttribute("employeeAndInfoPageBean",employeeAndInfoPageBean);

        return  "leftBox/employeeInfo";
    }
    
}
