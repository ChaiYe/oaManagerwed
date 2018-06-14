package com.officeAuto.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officeAuto.ssm.model.*;
import com.officeAuto.ssm.service.EmpAndInfoService;
import com.officeAuto.ssm.service.EmployeeService;
import com.officeAuto.ssm.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpAndInfoService empAndInfoService;

    //查询页面的大小
    private int pageSize = 5;

    /*******************************************************登录**********************************************************/
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
//        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");
        return "employeeHome";
    }

    /**
     * 加载图片
     * 以字节数组的形式
     * 用response输出到页面
     * @param fileName 要加载的图片文件名
     * @param response
     */
    @RequestMapping("/showPic/{fileName}")
    public void showPicture(@PathVariable("fileName") String fileName, HttpServletResponse response){
        //将文件名分割成 文件名 和 格式
        //“ . " 需要用两次转义
        String [] path = fileName.split("\\.");
        //获取服务器中的文件
        File imgFile = new File(Helper.rootPath + "employeeImage\\" + path[0] + "." + path[1]);
        //输出到页面
        responseFile(response, imgFile);
    }

    /**
     * 响应输出图片文件
     * 文件转换为字节数组
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile); OutputStream os = response.getOutputStream()){
            byte [] buffer = new byte[(int)imgFile.length()]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }


    /*******************************************************个人信息管理**********************************************************/

    /**
     * 更换头像
     * 图片异步上传
     * @param request
     * @param session
     * @return 写入数据库中的文件名，以json格式返回，便于页面获取新图片路径
     * @throws Exception
     */
    @RequestMapping("imgUpload")
    @ResponseBody
    public String imgUpload(HttpServletRequest request, HttpSession session) throws Exception{
        //获取上传的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //input file框的name属性，必须有，不然得不到文件
        MultipartFile file = multipartRequest.getFile("imgFile");
        //取得登录的员工信息
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");

        if(employeeAndInfo == null || file.isEmpty()) return null;

        //上传文件路径
        String path = Helper.rootPath + Helper.imgPath;

        //文件格式
        String originalFilename = file.getOriginalFilename();
        String format = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

        //修改后的文件名
        //以员工uuid作为文件名，每次上传覆盖原来相同格式的文件
        String filename = employeeAndInfo.getEmployeeInfo().getImage();
        if(filename == null || filename.equals(""))
            filename = employeeAndInfo.getUuid() + "." + format;

        //判断路径是否存在，如果不存在就创建一个
        File filepath = new File(path,filename);
        if (!filepath.getParentFile().exists())
            filepath.getParentFile().mkdirs();

        //文件的路径全名
        String longFileName = path + File.separator + filename;
        //将上传文件保存到一个目标文件当中
        file.transferTo(new File(longFileName));

        //写入数据库，只更新employeeInfo表的image字段
        empAndInfoService.updateImg(employeeAndInfo.getUuid(), filename);
        //重新设置session
        employeeAndInfo.getEmployeeInfo().setImage(filename);
        session.setAttribute("employee", employeeAndInfo);
        return filename;
    }

    /**
     * 账号设置页面
     * @return
     */
    @RequestMapping("accountSetPage")
    public String accountPage(){
        return "accountSetting";
    }

    /**
     * 账号更新操作
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("accountUpdate")
    @ResponseBody
    public String accountUpdate(@RequestBody Map<String, String> map, HttpSession session){
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");
        if(employeeAndInfo == null)
            return "非法用户，请重新登录";

        String account = map.get("account");
        if(empAndInfoService.accountUnique(account)){
            empAndInfoService.updateAccount(employeeAndInfo.getUuid(), account);
            employeeAndInfo.setAccount("account");
            session.setAttribute("employee", employeeAndInfo);
            return "修改成功";
        }
        else return "账号名已被使用，请重新输入";
    }

    /**
     * 跳转修改密码页面
     * @return
     */
    @RequestMapping("jumpToUpdate")
    public String jumpToUpdate(){
        return "retrieve_password";
    }

    @RequestMapping("forgetPassword")
    public @ResponseBody String forgetPassword(String phone,String password){

        Integer num=employeeService.findByPhone(phone);

        if(num>0)
        {
                empAndInfoService.forgetPassword(phone,password);
        }
        else{
            return "该手机号不存在";
        }


        return "成功更新密码";
    }

    /**
     * 更新密码
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("passwordUpdate")
    @ResponseBody
    public String passwordUpdate(@RequestBody Map<String, String> map, HttpSession session){
        EmployeeAndInfo employeeAndInfo = (EmployeeAndInfo)session.getAttribute("employee");
        if(employeeAndInfo == null)
            return "非法用户，请重新登录";

        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");

        if(!employeeAndInfo.getPassword().equals(oldPassword))
            return "密码错误请输入正确的密码";

        empAndInfoService.updatePassword(employeeAndInfo.getUuid(), newPassword);
        return "修改成功";
    }


    /**
     * 个人信息修改页面
     * @return 页面
     */
    @RequestMapping("infoEditPage")
    public String infoEditPage(){
        return "employeeInfoEdit";
    }

    /**
     * 个人信息更新
     * @param employeeInfo
     * @param session
     * @return
     */
    @RequestMapping("infoUpdate")
    public String infoUpdate(EmployeeInfo employeeInfo, HttpSession session){
        EmployeeAndInfo employee = (EmployeeAndInfo) session.getAttribute("employee");
        employeeInfo.setId(employee.getUuid());
        empAndInfoService.updateInfoSelective(employeeInfo);
        employee.setEmployeeInfo(employeeInfo);
        return "employeeInfoEdit";
    }



    /**********************************************************后台管理**********************************************************/
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
        return  "redirect:/employee/getEmployeeByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addEmployeeInfo";
    }

    @RequestMapping("insert")
    public String insert(Employee employee) throws Exception
    {
        employee.setCreatetime(new Date());
        employee.setState("在职");

        employeeService.insert(employee);
        return  "redirect:/employee/getEmployeeByPage.action";
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
        return "redirect:/employee/getEmployeeByPage.action";
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
        return "redirect:/employee/getEmployeeByPage.action";
    }

    @RequestMapping("add")
    public String add(Employee employee,EmployeeInfo employeeInfo) throws Exception
    {
        employee.setCreatetime(new Date());
        employee.setState("在职");
        employee.setUuid(employeeService.findCount()+1);
        employeeInfo.setId(employee.getUuid());
        employeeService.insert(employee);
        empAndInfoService.add(employeeInfo);
        return  "redirect:/employee/getEmployeeByPage.action";
    }

}
