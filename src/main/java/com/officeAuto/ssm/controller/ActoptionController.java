package com.officeAuto.ssm.controller;


import com.officeAuto.ssm.model.Actoption;
import com.officeAuto.ssm.model.ActoptionQueryModel;
import com.officeAuto.ssm.service.ActoptionService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/actoption")
public class ActoptionController {

    @Autowired
    private ActoptionService actoptionService;


    @RequestMapping("/getActoptionByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Actoption> actoptionPageBean= new PageBean<Actoption>();
        if(currentPage!=null)
        {
            actoptionPageBean.setCurrentPage(currentPage);
        }
        List<Actoption> actoptions=actoptionService.findByPage(actoptionPageBean);
        actoptionPageBean.setDatas(actoptions);

        Integer totalCount =actoptionService.findCount();
        actoptionPageBean.setTotalCount(totalCount);

        model.addAttribute("actoptionPageBean",actoptionPageBean);

        return  "leftBox/actoptionInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        actoptionService.deleteById(uuid);
        return  "redirect:/actoption/getActoptionByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addActoptionInfo";
    }

    @RequestMapping("insert")
    public String insert(Actoption actoption) throws Exception
    {
        /*actoption.setCreatetime(new Date());
        actoption.setDept(1);
        actoption.setEmployee(1);*/
        actoptionService.insert(actoption);
        return  "redirect:/actoption/getActoptionByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Actoption actoption=actoptionService.findById(uuid);
        model.addAttribute("actoption",actoption);
        return "leftBox/edit/editActoptionInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Actoption actoption) throws Exception
    {
        actoption.setUuid(uuid);
        actoptionService.update(actoption);
        return "redirect:/actoption/getActoptionByPage.action";
    }

   /* @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        ActoptionQueryModel actoptionQueryModel=new ActoptionQueryModel();
        actoptionQueryModel.setQueryAcount1(queryAcount1);
        actoptionQueryModel.setQueryAcount2(queryAcount2);
        PageBean<ActoptionQueryModel> pageBeanQuery= new PageBean<ActoptionQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("actoptionQuery",actoptionQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Actoption> actoptions=actoptionService.findByPageQuery(pageBeanQuery);

        PageBean<Actoption> actoptionPageBean= new PageBean<Actoption>();
        actoptionPageBean.setDatas(actoptions);

        model.addAttribute("actoptionPageBean",actoptionPageBean);
        return "leftBox/actoptionInfo";
    }*/


    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }



        actoptionService.delete(delitems);
        return "redirect:/actoption/getActoptionByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        actoptionService.findById(id);
        return "redirect:/actoption/getActoptionByPage.action";
    }

}
