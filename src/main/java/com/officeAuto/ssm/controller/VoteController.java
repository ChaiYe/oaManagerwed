package com.officeAuto.ssm.controller;



import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.ActivityService;
import com.officeAuto.ssm.service.ActvoteService;
import com.officeAuto.ssm.service.DeptService;
import com.officeAuto.ssm.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;
    @Autowired
    private ActvoteService actvoteService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private ActivityService activityService;


    @InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping("addVote")
    public String addVote(HttpSession session,Vote vote){


        Employee employee=(Employee) session .getAttribute("employee");

        vote.setEmployee(employee.getUuid());


        voteService.insert(vote);

        return "voteinfo";

    }

    @RequestMapping("jumpToAddVote")
    public String jumpToAddVote(String activity, Model model) throws Exception {
       /* List<Dept> depts =deptService.findAll();*/
        List<Activity> activities=activityService.findAll();
        model.addAttribute("activities",activities);
        /*model.addAttribute("depts",depts);*/
        model.addAttribute("activity",activity);
        return "voteinfo";
    }

    @RequestMapping("jumpToPersonVote")
    public  String jumpToPersonVote(HttpSession session,Integer voteId,Model model) throws Exception
    {
        Employee employee=(Employee) session .getAttribute("employee");

        if(actvoteService.checkVoted(voteId,employee.getUuid())>0)
        {
            model.addAttribute("hadVoted",true);

        }
        else{
            Date date=new Date();
            Vote vote=voteService.findById(voteId);
            model.addAttribute("date",date);
            model.addAttribute("vote",vote);
        }

        return "personVote";
    }


    @RequestMapping("jumpToPersonVoteList")
    public  String jumpToPersonVoteList(Integer activityId,Model model){

        List <Vote> votes= voteService.findByActivity(activityId);

        model.addAttribute("votes",votes);
        model.addAttribute("activityId",activityId);

        return "personVoteList";
    }
}
