package com.officeAuto.ssm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officeAuto.ssm.model.Announce;
import com.officeAuto.ssm.model.AnnounceQueryModel;
import com.officeAuto.ssm.model.EmployeeAndInfo;
import com.officeAuto.ssm.service.AnnounceService;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    private int pageSize = 5;

    @RequestMapping("/getAnnounceByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        if(currentPage==null)
            currentPage = 1;
        //在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        PageHelper.startPage(currentPage, pageSize);
        List<Announce> list = announceService.findAll();

        //PageInfo类包装数据
        PageInfo<Announce> p = new PageInfo<Announce>(list);

        model.addAttribute("page", p);
        model.addAttribute("list", list);

        return  "leftBox/announceInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        announceService.deleteById(uuid);
        return  "redirect:/announce/getAnnounceByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addAnnounceInfo";
    }

    @RequestMapping("insert")
    public String insert(Announce announce, HttpSession session) throws Exception
    {
        announce.setCreatetime(new Date());
        announce.setDept(1);
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");
        announce.setEmployee(employeeAndInfo.getUuid());
        announceService.insert(announce);
        return  "redirect:/announce/getAnnounceByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Announce announce=announceService.findById(uuid);
        model.addAttribute("announce",announce);
        return "leftBox/edit/editAnnounceInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Announce announce) throws Exception
    {
        announce.setUuid(uuid);
        announceService.update(announce);
        return "redirect:/announce/getAnnounceByPage.action";
    }

    @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        AnnounceQueryModel announceQueryModel=new AnnounceQueryModel();
        announceQueryModel.setQueryAcount1(queryAcount1);
        announceQueryModel.setQueryAcount2(queryAcount2);
        PageBean<AnnounceQueryModel> pageBeanQuery= new PageBean<AnnounceQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("announceQuery",announceQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Announce> announces=announceService.findByPageQuery(pageBeanQuery);

        PageBean<Announce> announcePageBean= new PageBean<Announce>();
        announcePageBean.setDatas(announces);

        model.addAttribute("announcePageBean",announcePageBean);
        return "leftBox/announceInfo";
    }


    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }



        announceService.delete(delitems);
        return "redirect:/announce/getAnnounceByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        announceService.findById(id);
        return "redirect:/announce/getAnnounceByPage.action";
    }

}
