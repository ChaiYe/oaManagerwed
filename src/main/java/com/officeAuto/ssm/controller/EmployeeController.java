package com.officeAuto.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.EmpAndInfoService;
import com.officeAuto.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpAndInfoService empAndInfoService;

    private int pageSize = 5;
    private int maxFileSize = 32505856;
    private String rootPath = "D:\\OAstorage\\";

    /**
     * 登录界面
     * @return 页面
     */
    @RequestMapping("/loginPage")
    public String login()
    {
        return "login";
    }

    /**
     * ajax登录
     * @param employee 员工账号密码
     * @param session
     * @return 账号密码是否匹配
     */
    @RequestMapping("/loginAjax")
    @ResponseBody
    public boolean loginAjax(@RequestBody Employee employee, HttpSession session){

        Employee e = employeeService.login(employee.getAccount(),employee.getPassword());

        //账号密码正确
        if (e != null){
            EmployeeAndInfo employeeAndInfo = empAndInfoService.getInfo(e.getUuid());
            //找出最高权限
            int authority = Integer.MIN_VALUE;
            Job job = new Job();
            for (Job j:employeeAndInfo.getJobs()) {
                if(j.getAuthority() > authority){
                    authority = j.getAuthority();
                    job = j;
                }
            }
            session.setAttribute("employee", employeeAndInfo);
            session.setAttribute("job", job);
            return true;
        }
        else
            return false;
    }

    /**
     * 注销操作
     * @param session 删除session中的实体
     * @return 登录页面
     */
    @RequestMapping("/logout")
    public String logOut(HttpSession session){
        session.setAttribute("employee", null);
        return "login";
    }

    /**
     * 进入个人页面
     * @return 页面
     */
    @RequestMapping("/employeeHome")
    public String employeeHomePage(HttpSession session, ModelMap modelMap){

        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");

        return "employeeHome";
    }

    @RequestMapping("/showPic/{fileName}")
    public void showPicture(@PathVariable("fileName") String fileName, HttpServletResponse response){

        String [] path = fileName.split("\\.");
        File imgFile = new File(rootPath + "employeeImage\\" + path[0] + "." + path[1]);
        responseFile(response, imgFile);
    }

    @RequestMapping("imgUpload")
    @ResponseBody
    public boolean imgUpload(HttpServletRequest request, HttpSession session) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("imgFile");
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");

        if(employeeAndInfo == null || file.isEmpty()) return false;

        //上传文件路径
        String path = rootPath + "employeeImage";

        //文件格式
        String originalFilename = file.getOriginalFilename();
        String format = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

        //上传文件名
        String filename = employeeAndInfo.getEmployeeInfo().getImage();
        if(filename == null || filename.equals(""))
            filename = employeeAndInfo.getUuid() + "." + format;

        File filepath = new File(path,filename);

        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        //文件的路径全名
        String longFileName = path + File.separator + filename;
        //将上传文件保存到一个目标文件当中
        file.transferTo(new File(longFileName));

        //写入数据库
        empAndInfoService.updateImg(employeeAndInfo.getUuid(), filename);
        employeeAndInfo.getEmployeeInfo().setImage(longFileName);
        session.setAttribute("employee", employeeAndInfo);
        return true;
    }

    /**
     * 进入管理页面
     * @return 页面
     */
    @RequestMapping("/managePage")
    public String managePage(){
        return "management";
    }

    /**
     * 主站显示页面
     * @return
     */
    @RequestMapping("/homePage")
    public String homePage(){
        return "leftBox/homepage";
    }

    /**
     * 分页查询员工信息
     * @param currentPage 当前页面
     * @param model 模型
     * @return 页面
     * @throws Exception
     */
    @RequestMapping("/getEmployeeByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        if(currentPage==null)
            currentPage = 1;
        //在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        PageHelper.startPage(currentPage, pageSize);
        List<EmployeeAndInfo> list = empAndInfoService.findAll();

        //PageInfo类包装数据
        PageInfo<EmployeeAndInfo> p = new PageInfo<EmployeeAndInfo>(list);

        model.addAttribute("page", p);
        model.addAttribute("list", list);
        return "leftBox/employeeInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        employeeService.deleteById(uuid);
        return  "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addEmployeeInfo";
    }

    @RequestMapping("insert")
    public String insert(Employee Employee) throws Exception
    {
        Employee.setCreatetime(new Date());
        /*Employee.setDept(1);
        Employee.setEmployee(1);*/
        employeeService.insert(Employee);
        return  "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Employee Employee= employeeService.findById(uuid);
        model.addAttribute("Employee",Employee);
        return "leftBox/edit/editEmployeeInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Employee Employee) throws Exception
    {
        Employee.setUuid(uuid);
        employeeService.update(Employee);
        return "redirect:/Employee/getEmployeeByPage.action";
    }

    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }

        employeeService.delete(delitems);
        return "redirect:/Employee/getEmployeeByPage.action";
    }


    /* 文件转换为字节数组*/
    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[maxFileSize]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
