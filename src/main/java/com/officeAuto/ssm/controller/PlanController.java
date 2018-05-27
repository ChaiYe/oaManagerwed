package com.officeAuto.ssm.controller;


import com.officeAuto.ssm.model.Plan;
import com.officeAuto.ssm.model.PlanQueryModel;
import com.officeAuto.ssm.service.PlanService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;


    @RequestMapping("/getPlanByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Plan> planPageBean= new PageBean<Plan>();
        if(currentPage!=null)
        {
            planPageBean.setCurrentPage(currentPage);
        }
        List<Plan> plans=planService.findByPage(planPageBean);
        planPageBean.setDatas(plans);

        Integer totalCount =planService.findCount();
        planPageBean.setTotalCount(totalCount);

        model.addAttribute("planPageBean",planPageBean);

        return  "leftBox/planInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        planService.deleteById(uuid);
        return  "redirect:/plan/getPlanByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addPlanInfo";
    }

    @RequestMapping("insert")
    public String insert(Plan plan) throws Exception
    {
        plan.setState("进行中");
        planService.insert(plan);
        return  "redirect:/plan/getPlanByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Plan plan=planService.findById(uuid);
        model.addAttribute("plan",plan);
        return "leftBox/edit/editPlanInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Plan plan) throws Exception
    {
        plan.setUuid(uuid);
        planService.update(plan);
        return "redirect:/plan/getPlanByPage.action";
    }

    /*@RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        PlanQueryModel planQueryModel=new PlanQueryModel();
        planQueryModel.setQueryAcount1(queryAcount1);
        planQueryModel.setQueryAcount2(queryAcount2);
        PageBean<PlanQueryModel> pageBeanQuery= new PageBean<PlanQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("planQuery",planQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Plan> plans=planService.findByPageQuery(pageBeanQuery);

        PageBean<Plan> planPageBean= new PageBean<Plan>();
        planPageBean.setDatas(plans);

        model.addAttribute("planPageBean",planPageBean);
        return "leftBox/planInfo";
    }*/


    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }



        planService.delete(delitems);
        return "redirect:/plan/getPlanByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        planService.findById(id);
        return "redirect:/plan/getPlanByPage.action";
    }

}
