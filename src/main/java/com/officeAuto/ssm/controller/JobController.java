package com.officeAuto.ssm.controller;


import com.officeAuto.ssm.model.Job;
import com.officeAuto.ssm.model.JobQueryModel;
import com.officeAuto.ssm.service.JobService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;


    @RequestMapping("/getJobByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Job> jobPageBean= new PageBean<Job>();
        if(currentPage!=null)
        {
            jobPageBean.setCurrentPage(currentPage);
        }
        List<Job> jobs=jobService.findByPage(jobPageBean);
        jobPageBean.setDatas(jobs);

        Integer totalCount =jobService.findCount();
        jobPageBean.setTotalCount(totalCount);

        model.addAttribute("jobPageBean",jobPageBean);

        return  "leftBox/jobInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        jobService.deleteById(uuid);
        return  "redirect:/job/getJobByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addJobInfo";
    }

    @RequestMapping("insert")
    public String insert(Job job) throws Exception
    {
        //job.setCreatetime(new Date());
        job.setDept(1);
        job.setEmployee(1);
        jobService.insert(job);
        return  "redirect:/job/getJobByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Job job=jobService.findById(uuid);
        model.addAttribute("job",job);
        return "leftBox/edit/editJobInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Job job) throws Exception
    {
        job.setUuid(uuid);
        jobService.update(job);
        return "redirect:/job/getJobByPage.action";
    }

  /*  @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        JobQueryModel jobQueryModel=new JobQueryModel();
        jobQueryModel.setQueryAcount1(queryAcount1);
        jobQueryModel.setQueryAcount2(queryAcount2);
        PageBean<JobQueryModel> pageBeanQuery= new PageBean<JobQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("jobQuery",jobQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Job> jobs=jobService.findByPageQuery(pageBeanQuery);

        PageBean<Job> jobPageBean= new PageBean<Job>();
        jobPageBean.setDatas(jobs);

        model.addAttribute("jobPageBean",jobPageBean);
        return "leftBox/jobInfo";
    }*/


    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }



        jobService.delete(delitems);
        return "redirect:/job/getJobByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        jobService.findById(id);
        return "redirect:/job/getJobByPage.action";
    }

}
