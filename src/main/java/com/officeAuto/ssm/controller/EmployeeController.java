package com.officeAuto.ssm.controller;

import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.EmpAndInfoService;
import com.officeAuto.ssm.service.EmployeeService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpAndInfoService empAndInfoService;

    /**
     * 登录界面
     * @return 页面
     */
    @RequestMapping("/loginPage")
    public String login()
    {
        return "login";
    }

    /**
     * 登录操作
     * @param account 账号
     * @param password 密码
     * @param session 全局session
     * @return 页面
     */
    @RequestMapping("/login")
    public  String login(String account, String password, HttpSession session) throws Exception {

        Employee employee = employeeService.login(account,password);

        if(employee==null)
            return "login";

        EmployeeAndInfo employeeAndInfo = empAndInfoService.findByUuid(employee.getUuid());


        session.setAttribute("employee", employee);

        return  "management";
    }

    @RequestMapping("/loginAjax")
    @ResponseBody
    public boolean loginAjax(@RequestBody Employee employee, HttpSession session){

        EmployeeAndInfo employeeAndInfo = empAndInfoService.login(employee.getAccount(),employee.getPassword());
        //账号密码正确
        if (employeeAndInfo != null){

            int authority = 0;
            for (Job j:employeeAndInfo.getJobs()) {
                if(j.getAuthority() > authority)
                    authority = j.getAuthority();
            }
            session.setAttribute("emplpyee", employeeAndInfo);
            session.setAttribute("authority", authority);
            return true;
        }
        else
            return false;
    }

    /**
     * 进入管理页面
     * @return 页面
     */
    @RequestMapping("/managePage")
    public String managePage(){
        return "management";
    }

    /**
     * 主站显示页面
     * @return
     */
    @RequestMapping("/homePage")
    public String homePage(){
        return "leftBox/homepage";
    }

    /**
     * 注销操作
     * @param session 删除session中的实体
     * @return 登录页面
     */
    @RequestMapping("/logout")
    public String logOut(HttpSession session){
        session.setAttribute("employee", null);
        return "login";
    }

    /**
     * 分页查询员工信息
     * @param currentPage 当前页面
     * @param model 模型
     * @return 页面
     * @throws Exception
     */
    @RequestMapping("/getEmployeeByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Employee> EmployeePageBean= new PageBean<Employee>();
        if(currentPage!=null)
        {
            EmployeePageBean.setCurrentPage(currentPage);
        }

        List<Employee> Employees = employeeService.findByPage(EmployeePageBean);
        EmployeePageBean.setDatas(Employees);

        Integer totalCount = employeeService.findCount();
        EmployeePageBean.setTotalCount(totalCount);

        model.addAttribute("EmployeePageBean",EmployeePageBean);

        return  "leftBox/EmployeeInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        employeeService.deleteById(uuid);
        return  "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addEmployeeInfo";
    }

    @RequestMapping("insert")
    public String insert(Employee Employee) throws Exception
    {
        Employee.setCreatetime(new Date());
        /*Employee.setDept(1);
        Employee.setEmployee(1);*/
        employeeService.insert(Employee);
        return  "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Employee Employee= employeeService.findById(uuid);
        model.addAttribute("Employee",Employee);
        return "leftBox/edit/editEmployeeInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Employee Employee) throws Exception
    {
        Employee.setUuid(uuid);
        employeeService.update(Employee);
        return "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }

        employeeService.delete(delitems);
        return "redirect:/Employee/getEmployeeByPage.action";
    }
}
