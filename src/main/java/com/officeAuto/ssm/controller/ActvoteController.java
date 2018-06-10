package com.officeAuto.ssm.controller;


import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.ActvoteService;
import com.officeAuto.ssm.utils.Helper;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/actvote")
public class ActvoteController {

    @Autowired
    private ActvoteService actvoteService;

    @RequestMapping("/getActvoteByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Actvote> actvotePageBean= new PageBean<Actvote>();
        if(currentPage!=null)
        {
            actvotePageBean.setCurrentPage(currentPage);
        }
        List<Actvote> actvotes=actvoteService.findByPage(actvotePageBean);
        actvotePageBean.setDatas(actvotes);

        Integer totalCount =actvoteService.findCount();
        actvotePageBean.setTotalCount(totalCount);

        model.addAttribute("actvotePageBean",actvotePageBean);

        return  "leftBox/actvoteInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        actvoteService.deleteById(uuid);
        return  "redirect:/actvote/getActvoteByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addActvoteInfo";
    }

    @RequestMapping("insert")
    public String insert(Actvote actvote) throws Exception
    {
       /* actvote.setCreatetime(new Date());
        actvote.setDept(1);*/
        actvote.setEmployee(1);
        actvoteService.insert(actvote);
        return  "redirect:/actvote/getActvoteByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Actvote actvote=actvoteService.findById(uuid);
        model.addAttribute("actvote",actvote);
        return "leftBox/edit/editActvoteInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Actvote actvote) throws Exception
    {
        actvote.setUuid(uuid);
        actvoteService.update(actvote);
        return "redirect:/actvote/getActvoteByPage.action";
    }

 /*   @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        ActvoteQueryModel actvoteQueryModel=new ActvoteQueryModel();
        actvoteQueryModel.setQueryAcount1(queryAcount1);
        actvoteQueryModel.setQueryAcount2(queryAcount2);
        PageBean<ActvoteQueryModel> pageBeanQuery= new PageBean<ActvoteQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("actvoteQuery",actvoteQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Actvote> actvotes=actvoteService.findByPageQuery(pageBeanQuery);

        PageBean<Actvote> actvotePageBean= new PageBean<Actvote>();
        actvotePageBean.setDatas(actvotes);

        model.addAttribute("actvotePageBean",actvotePageBean);
        return "leftBox/actvoteInfo";
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

        actvoteService.delete(delitems);
        return "redirect:/actvote/getActvoteByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        actvoteService.findById(id);
        return "redirect:/actvote/getActvoteByPage.action";
    }

}
