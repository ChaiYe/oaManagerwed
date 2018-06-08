package com.officeAuto.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.AnnounceService;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.utils.Helper;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

@Controller
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    private int pageSize = 5;
    private Helper helper;
    /**
     * 公司公告
     * @return
     */
    @RequestMapping("recentCompAjax")
    @ResponseBody
    public List getCompRecent(){
        List<Announce> list = announceService.getRecentAnnounce("公司", 1);
        return list;
    }

    /**
     * 部门公告
     * 查询出当前用户在职的所有部门最近的公告
     * @param session
     * @return
     */
    @RequestMapping("recentDeptAjax")
    @ResponseBody
    public List getDeptRecent(HttpSession session){
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo) session.getAttribute("employee");

        List<JobQueryModel> jobs = employeeAndInfo.getJobs();
//        List<Announce> announces = new ArrayList<>();
        List<AnnounceDpet> announces = new ArrayList<>();
        List<Integer> deps = new ArrayList<>();

        //循环该员工所有职位
        for (JobQueryModel j : jobs) {
            if(j.getDepart().getName().equals("公司"))
                continue;
            //判断是否重复
            boolean repeat = false;
            int temp = j.getDepart().getUuid();
            for(int i : deps) {
                if (i == temp) {
                    repeat = true;
                    break;
                }
            }
            //重复跳过
            if(repeat) continue;

            //不重复
            deps.add(temp);
            //将该部门的公告查询出来
//            List<Announce> list = announceService.getRecentAnnounce(j.getDepart().getName(), 2);
            List<AnnounceDpet> list = announceService.getAnnounceDept(j.getDept(), 2);
            announces.addAll(list);
        }
        return announces;
    }

    /**
     * 添加公告
     * 遍历找出当前员工有权限发布公告的部门
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("addAnnouncePage")
    public String addAnnPage(HttpSession session, ModelMap modelMap){
        List<JobQueryModel> jobs = ((EmployeeAndInfo)session.getAttribute("employee")).getJobs();
        List<JobQueryModel> list = new ArrayList<>();
        //遍历找出有权限的部门，并去除重复
        helper.jobsOption(jobs, list);
        modelMap.addAttribute("jobs", list);
        return  "add_Announce";
    }

    /**
     * 发布公告
     * 持久化到数据库中
     * @param announce
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("addAnnounce")
    public String addAnn(Announce announce, HttpSession session) throws Exception {
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");
        announce.setEmployee(employeeAndInfo.getUuid());
        announce.setCreatetime(new Date());
        announceService.insert(announce);
        return "employeeHome";
    }



    /***************************************************后台公告管理*************************************************/
    @RequestMapping("/getAnnounceByPage")
    public String listPage(Integer currentPage,Model model) throws Exception {
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

    @RequestMapping("jumpToAllAnnounce")
    public String jumpToAllAnnounce(HttpSession session,Model model){




        return "allAnnounce";
    }

    @RequestMapping("allAnnounceJson")
    public  @ResponseBody List<AnnounceDpet> allAnnounceJson(Integer nowSize,HttpSession session) {

        if(nowSize==null){
            nowSize=0;
        }

        Employee employee= (Employee) session.getAttribute("employee");

        List<AnnounceDpet> announceDpets=announceService.findAllWithDept(employee.getUuid(),nowSize);

        return announceDpets;
    }
}
