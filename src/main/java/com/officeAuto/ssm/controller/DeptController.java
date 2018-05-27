package com.officeAuto.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.model.DeptQueryModel;
import com.officeAuto.ssm.model.Dept;
import com.officeAuto.ssm.model.Employee;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    @RequestMapping("/getDeptByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Dept> deptPageBean= new PageBean<Dept>();
        if(currentPage!=null)
        {
            deptPageBean.setCurrentPage(currentPage);
        }
        List<Dept> depts=deptService.findByPage(deptPageBean);
        deptPageBean.setDatas(depts);

        Integer totalCount =deptService.findCount();
        deptPageBean.setTotalCount(totalCount);

        model.addAttribute("deptPageBean",deptPageBean);

        return  "leftBox/deptInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        deptService.deleteById(uuid);
        return  "redirect:/dept/getDeptByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addDeptInfo";
    }

    @RequestMapping("insert")
    public String insert(Dept dept) throws Exception
    {
        deptService.insert(dept);
        return  "redirect:/dept/getDeptByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Dept dept=deptService.findById(uuid);
        model.addAttribute("dept",dept);
        return "leftBox/edit/editDeptInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Dept dept) throws Exception
    {
        dept.setUuid(uuid);
        deptService.update(dept);
        return "redirect:/dept/getDeptByPage.action";
    }

   /* @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        DeptQueryModel deptQueryModel=new DeptQueryModel();
        deptQueryModel.setQueryAcount1(queryAcount1);
        deptQueryModel.setQueryAcount2(queryAcount2);
        PageBean<DeptQueryModel> pageBeanQuery= new PageBean<DeptQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("deptQuery",deptQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Dept> depts=deptService.findByPageQuery(pageBeanQuery);

        PageBean<Dept> deptPageBean= new PageBean<Dept>();
        deptPageBean.setDatas(depts);

        model.addAttribute("deptPageBean",deptPageBean);
        return "leftBox/deptInfo";
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



        deptService.delete(delitems);
        return "redirect:/dept/getDeptByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        deptService.findById(id);
        return "redirect:/dept/getDeptByPage.action";
    }


}
