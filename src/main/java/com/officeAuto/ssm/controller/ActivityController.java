package com.officeAuto.ssm.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.ActMarkerService;
import com.officeAuto.ssm.service.ActfileService;
import com.officeAuto.ssm.service.ActivityService;
import com.officeAuto.ssm.utils.Helper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActMarkerService actMarkerService;
    @Autowired
    private ActfileService actfileService;

    private int pageSize = 5;

    @RequestMapping("recentActivity")
    @ResponseBody
    public List<ActivityQueryModel> getRecentActivity(HttpSession session) throws Exception {
        //查出该员工所有不重复的部门
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session .getAttribute("employee");
        List<JobQueryModel> jobs = employeeAndInfo.getJobs();
        List<JobQueryModel> list = Helper.jobNoRepeat(jobs);

        //登录员工的所有任职部门近期活动
        List<ActivityQueryModel> activityList = new ArrayList<>();
        for (JobQueryModel j : list){
            //获取活动
            List<ActivityQueryModel> temp = activityService.getRecentActDept(j.getDept(), 2);
            //遍历活动，获取每个活动的里程碑
            for(ActivityQueryModel act : temp)
                act.setMarkers(actMarkerService.getActivityMarker(act.getUuid(), 5));
            //加入所有查询出来的记录
            activityList.addAll(temp);
        }

        return activityList;
    }

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
        Helper.jobsOption(jobs, list);
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

        //失败信息
        if(employeeAndInfo == null){
            isSuccess = false;
            message = "非法用户，请重新登录";
        }
        else{
            //设置数据
            Activity activity = new Activity();
            activity.setEmployee(employeeAndInfo.getUuid());
            activity.setDept(Integer.parseInt(map.get("dept")));
            Date begin = Helper.convert(map.get("begintime"));
            Date now = new Date();
            activity.setBegintime(begin);
            activity.setEndtime(Helper.convert(map.get("endtime")));
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

    /**
     * 进入活动的详情页
     * @param actid
     * @param session
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("activityDetailPage/{activity}")
    public String activityDetailPage(@PathVariable(value="activity") Integer actid, HttpSession session, ModelMap model) throws Exception {
        ActivityQueryModel activityQueryModel = activityService.getById(actid);
        model.addAttribute("activity", activityQueryModel);
        return "activityDetail";
    }

    /**
     * 获取活动里程碑
     * @param actid
     * @return
     * @throws Exception
     */
    @RequestMapping("getActMarkersAjax/{actid}")
    @ResponseBody
    public List<Actmarker> getActMarkerAjax(@PathVariable("actid")Integer actid) throws Exception {
        List<Actmarker> list = actMarkerService.getActivityMarker(actid, 10);
        return list;
    }

    /**
     * 当前用户是否有权限为活动添加里程碑
     * 活动的申请人，或者高权限者
     * @param deptid
     * @param session
     * @return
     */
    @RequestMapping("showAddMarker/{applyEmpl}/{deptid}")
    @ResponseBody
    public boolean showAddMarker(@PathVariable("deptid") Integer deptid, @PathVariable("applyEmpl")Integer applyEmpl, HttpSession session){
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");

        if(employeeAndInfo == null)
            return false;
        //申请人
        if(employeeAndInfo.getUuid().equals(applyEmpl))
            return true;

        List<JobQueryModel> jobs = employeeAndInfo.getJobs();
        //该部门中高权限的人
        for(JobQueryModel j : jobs){
            if(j.getDept().equals(deptid) && j.getAuthority() > 1)
                return true;
        }
        return false;
    }

    /**
     * 添加活动里程碑
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("addMarkerAjax")
    @ResponseBody
    public boolean addMarkerAjax(@RequestBody Map<String, String> map) throws Exception {
        Integer actid = Integer.parseInt(map.get("actid"));
        String descript = map.get("marker");

        Actmarker actmarker = new Actmarker();
        actmarker.setActivity(actid);
        actmarker.setDescript(descript);
        actmarker.setCreatetime(new Date());
        return actMarkerService.insert(actmarker) == 1;
    }

    /**
     * 上传文件
     * @param actid 活动id
     * @param session
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("uploadFile/{actid}")
    @ResponseBody
    public Map<String, Object> fileUpload(@PathVariable("actid")Integer actid, HttpSession session,
                             HttpServletRequest request) throws IOException {
        //获取上传的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //input file框的name属性，必须有，不然得不到文件
        MultipartFile file = multipartRequest.getFile("file");

        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");
        Map<String, Object> dataMap = new HashMap<>();
        String resultMsg = "";
        if(employeeAndInfo == null){
            dataMap.put("resultMsg", "非法用户，请重新登录");
            return dataMap;
        }
        if(file.isEmpty()){
            dataMap.put("resultMsg", "文件错误");
            return dataMap;
        }

        try {
            Long size = file.getSize();
            // 获取原文件名
            String oriName = file.getOriginalFilename();
            // 获取原文件图片后缀，以最后的.作为截取
            String extName = oriName.substring(oriName.lastIndexOf("."));
            // 生成自定义的新文件名，这里以UUID.xxx作为格式（可以不自定义新文件名）
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + extName;
            //定义保存路径，为rootPath下的以活动主键为名的文件夹下
            String path = Helper.rootPath + actid;

            //判断路径是否存在，如果不存在就创建一个
            File filepath = new File(path, filename);
            if (!filepath.getParentFile().exists())
                filepath.getParentFile().mkdirs();
            //文件的路径全名
            String longFileName = path + File.separator + filename;
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(longFileName));

            //持久化
            Actfile actfile = new Actfile();
            actfile.setActivity(actid);
            actfile.setEmployee(employeeAndInfo.getUuid());
            actfile.setCreatetime(new Date());
            actfile.setSize(size.intValue());
            actfile.setFormat(extName);
            actfile.setName(oriName);
            actfile.setPath(longFileName);
            actfileService.insert(actfile);

            resultMsg = oriName + "上传成功";
            //成功上传返回信息，code = 1 为成功 0 为失败
            dataMap.put("resultMsg", resultMsg);
            dataMap.put("code", 1);
            return dataMap;
        } catch (IllegalStateException | IOException e) {
            resultMsg = "上传失败";
            dataMap.put("resultMsg", resultMsg);
            dataMap.put("code", 0);
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = "上传失败";
            dataMap.put("resultMsg", resultMsg);
            dataMap.put("code", 0);
            return dataMap;
        }
    }

    /**
     * 获取所有文件
     * @param actid
     * @return
     * @throws Exception
     */
    @RequestMapping("getActFiles/{actid}")
    @ResponseBody
    public List<ActFileAssociate> getActFiles(@PathVariable("actid")Integer actid) throws Exception {
        return actfileService.getActFileByAct(actid, null);
    }

    /**
     * 下载文件
     * @param fileId
     * @return
     * @throws Exception
     */
    @RequestMapping("/download/{fileid}") //匹配的是href中的download请求
    public ResponseEntity<byte[]> download(@PathVariable("fileid")Integer fileId) throws Exception {

        Actfile actFile = actfileService.findById(fileId);

        //新建一个文件
        File file = new File(actFile.getPath());
        //http头信息
        HttpHeaders headers = new HttpHeaders();
        //设置编码
        String downloadFileName = new String(actFile.getName().getBytes("UTF-8"),"iso-8859-1");
        // 以下载方式打开文件
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
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


    @RequestMapping("/getActivityLater")
    public @ResponseBody
   Activity getActivityLater() throws Exception{
        Activity activity=activityService.findById(1);
        return activity;
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
