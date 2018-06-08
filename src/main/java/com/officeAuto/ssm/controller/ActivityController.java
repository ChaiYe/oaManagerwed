package com.officeAuto.ssm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.ActivityService;
import com.officeAuto.ssm.utils.DateConverter;
import com.officeAuto.ssm.utils.Helper;
import com.officeAuto.ssm.utils.PageBean;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    private int pageSize = 5;
    private Helper helper;

    /**
     * 添加活动
     * 遍历找出当前员工有权限发布公告的部门
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("addActivityPage")
    public String addActivityPage(HttpSession session, ModelMap modelMap){
        List<JobQueryModel> jobs = ((EmployeeAndInfo)session.getAttribute("employee")).getJobs();
        List<JobQueryModel> list = new ArrayList<>();
        //遍历找出有权限的部门，并去除重复
        helper.jobsOption(jobs, list);
        modelMap.addAttribute("jobs", list);
        return "add_activity";
    }

    /**
     * 添加活动（异步）
     *
     * @param map map中含有activity实体的数据，具体请看页面
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("addActivityAjax")
    @ResponseBody
    public Map addActivity(@RequestBody Map<String, String> map, HttpSession session) throws Exception {
        //返回的数据
        Map<String, Object> result = new HashMap<>();
        boolean isSuccess;
        String message;
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");

        if(employeeAndInfo == null){
            //失败信息
            isSuccess = false;
            message = "非法用户，请重新登录";
        }
        else{
            //设置数据
            Activity activity = new Activity();
            activity.setEmployee(employeeAndInfo.getUuid());
            activity.setDept(Integer.parseInt(map.get("dept")));
            Date begin = helper.convert(map.get("begintime"));
            Date now = new Date();
            activity.setBegintime(begin);
            activity.setEndtime(helper.convert(map.get("endtime")));
            activity.setDescript(map.get("descript"));
            activity.setName(map.get("title"));
            //设置状态，开始时间在当前时间之后为1， 1是未开始， 2 是正在进行
            activity.setState(now.before(begin) ? 1 : 2);
            //持久化
            activityService.insert(activity);
            //成功信息
            isSuccess = true;
            message = "成功发布，请到详情页查看或修改";
        }
        //返回信息
        result.put("isSuccess", isSuccess);
        result.put("message", message);
        return result;
    }

    @RequestMapping("activityDetailPage")
    public String activityDetailPage(){
        return "activityDetail";
    }



/*******************************************************后台**********************************************************/
    /**
     * pagehelper 分页获取数据
     * @param currentPage 当前页面
     * @param model 模型
     * @return 页面
     * @throws Exception 异常
     */
    @RequestMapping("/getActivityByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        if(currentPage==null)
            currentPage = 1;
        //在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        PageHelper.startPage(currentPage, pageSize);
        List<Activity> list = activityService.findAll();

        //PageInfo类包装数据
        PageInfo<Activity> p = new PageInfo<Activity>(list);

        model.addAttribute("page", p);
        model.addAttribute("list", list);
        return "leftBox/activityInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        activityService.deleteById(uuid);
        return  "redirect:/activity/getActivityByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addActivityInfo";
    }

    @RequestMapping("insert")
    public String insert(Activity activity) throws Exception
    {
        activity.setState(1);
        activity.setBegintime(new Date());
        activity.setEndtime(new Date());
        activity.setEmployee(1);
        activityService.insert(activity);
        return  "redirect:/activity/getActivityByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Activity activity=activityService.findById(uuid);
        model.addAttribute("activity",activity);
        return "leftBox/edit/editActivityInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Activity activity) throws Exception
    {
        activity.setUuid(uuid);
        activityService.update(activity);
        return "redirect:/activity/getActivityByPage.action";
    }

    /*@RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        ActivityQueryModel activityQueryModel=new ActivityQueryModel();
        activityQueryModel.setQueryAcount1(queryAcount1);
        activityQueryModel.setQueryAcount2(queryAcount2);
        PageBean<ActivityQueryModel> pageBeanQuery= new PageBean<ActivityQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("activityQuery",activityQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Activity> activitys=activityService.findByPageQuery(pageBeanQuery);

        PageBean<Activity> activityPageBean= new PageBean<Activity>();
        activityPageBean.setDatas(activitys);

        model.addAttribute("activityPageBean",activityPageBean);
        return "leftBox/activityInfo";
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



        activityService.delete(delitems);
        return "redirect:/activity/getActivityByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id) throws  Exception
    {
        activityService.findById(id);
        return "redirect:/activity/getActivityByPage.action";
    }

}
