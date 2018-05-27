package com.officeAuto.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.EmployeeService;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.service.EmployeeService;
import com.officeAuto.ssm.utils.PageBean;
import jdk.nashorn.internal.runtime.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/loginPage")
    public String login(HttpServletRequest request)
    {
        return "login";
    }



    /*@RequestMapping("/login")
    public String login(HttpServletRequest request,String account,String password, Model model,HttpSession session){



        Employee employee=employeeService.login(account,password);

        if(employee==null){
            return "login";
        }

        session.setAttribute("employee", employee);



        return  "management";
    }
*/

    /*
    *   @RequestMapping("/loginPage")
    public String login(HttpServletRequest request)
    {
        return "login";
    }



    @RequestMapping("/login")
    public @ResponseBody
    String login(HttpServletRequest request,String account,String password, Model model,HttpSession session){



        Employee employee=employeeService.login(account,password);

        if(employee==null){
            return "login";
        }

        session.setAttribute("employee", employee);



        return  "management";
    }
    * */
    /*@RequestMapping("/list")
    public String list(HttpServletRequest request,Model model) {

        List<EmployeeAndInfo> employeeAndInfos= employeeService.findEmpInfoList();

        model.addAttribute("employeeAndInfos",employeeAndInfos);

        return "leftBox/employeeInfo";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request,Integer id)
    {
        //TODO
        employeeService.deleteByPrimaryKey(id);
        return null;
    }*/

   /* @RequestMapping("/add")
    public String  add(HttpServletRequest request,EmployeeAndInfo employeeAndInfo)
    {
        employeeAndInfo.setState("在职");
        employeeAndInfo.setCreatetime(new Date());
        employeeService.insert(employeeAndInfo);

        return "redirect:/employee/list.action";
    }*/

    @Autowired
    private EmployeeService EmployeeService;


    @RequestMapping("/getEmployeeByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Employee> EmployeePageBean= new PageBean<Employee>();
        if(currentPage!=null)
        {
            EmployeePageBean.setCurrentPage(currentPage);
        }
        List<Employee> Employees=EmployeeService.findByPage(EmployeePageBean);
        EmployeePageBean.setDatas(Employees);

        Integer totalCount =EmployeeService.findCount();
        EmployeePageBean.setTotalCount(totalCount);

        model.addAttribute("EmployeePageBean",EmployeePageBean);

        return  "leftBox/EmployeeInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        EmployeeService.deleteById(uuid);
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
        EmployeeService.insert(Employee);
        return  "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Employee Employee=EmployeeService.findById(uuid);
        model.addAttribute("Employee",Employee);
        return "leftBox/edit/editEmployeeInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Employee Employee) throws Exception
    {
        Employee.setUuid(uuid);
        EmployeeService.update(Employee);
        return "redirect:/Employee/getEmployeeByPage.action";
    }

   /* @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        EmployeeQueryModel EmployeeQueryModel=new EmployeeQueryModel();
        EmployeeQueryModel.setQueryAcount1(queryAcount1);
        EmployeeQueryModel.setQueryAcount2(queryAcount2);
        PageBean<EmployeeQueryModel> pageBeanQuery= new PageBean<EmployeeQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("EmployeeQuery",EmployeeQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Employee> Employees=EmployeeService.findByPageQuery(pageBeanQuery);

        PageBean<Employee> EmployeePageBean= new PageBean<Employee>();
        EmployeePageBean.setDatas(Employees);

        model.addAttribute("EmployeePageBean",EmployeePageBean);
        return "leftBox/EmployeeInfo";
    }
*/

    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }



        EmployeeService.delete(delitems);
        return "redirect:/Employee/getEmployeeByPage.action";
    }


    @RequestMapping("/login")
    public  String login(HttpServletRequest request,String account,String password, Model model,HttpSession session) throws  Exception
    {
        Employee employee=employeeService.login(account,password);

        if(employee==null){
            return "login";
        }

        session.setAttribute("employee", employee);

        return  "management";
    }
   
}
