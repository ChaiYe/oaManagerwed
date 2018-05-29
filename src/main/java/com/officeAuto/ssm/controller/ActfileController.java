package com.officeAuto.ssm.controller;


import com.officeAuto.ssm.model.Actfile;

import com.officeAuto.ssm.service.ActfileService;
import com.officeAuto.ssm.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/actfile")
public class ActfileController {

    @Autowired
    private ActfileService actfileService;


    @RequestMapping("/getActfileByPage")
    public String listPage(Integer currentPage,Model model) throws Exception
    {
        PageBean<Actfile> actfilePageBean= new PageBean<Actfile>();
        if(currentPage!=null)
        {
            actfilePageBean.setCurrentPage(currentPage);
        }
        List<Actfile> actfiles=actfileService.findByPage(actfilePageBean);
        actfilePageBean.setDatas(actfiles);

        Integer totalCount =actfileService.findCount();
        actfilePageBean.setTotalCount(totalCount);

        model.addAttribute("actfilePageBean",actfilePageBean);

        return  "leftBox/actfileInfo";
    }

    @RequestMapping("delete")
    public String delete(Integer uuid) throws Exception
    {
        actfileService.deleteById(uuid);
        return  "redirect:/actfile/getActfileByPage.action";
    }

    @RequestMapping("jumpToAdd")
    public String jumpToAdd()
    {
        return  "leftBox/add/addActfileInfo";
    }

    @RequestMapping("insert")
    public String insert(@RequestParam(value="filename", required=false) MultipartFile file) throws Exception
    {
        Actfile actfile=new Actfile();
        if(file!=null && file.getOriginalFilename()!=null && file.getOriginalFilename().length()>0){
            //图片上传成功后，将图片的地址写到数据库
            String filePath = "D:\\OAstorage\\";
            //上传文件原始名称
            String originalFilename = file.getOriginalFilename();
            //获取上传文件的大小
            int size= (int) file.getSize();
            //获取上传文件的格式
            String format = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            //新的图片名称
            String newFileName = UUID.randomUUID() +originalFilename.substring(originalFilename.lastIndexOf("."));
            //新文件
            File newfile = new java.io.File(filePath+newFileName);

            //将内存中的文件写入磁盘
            file.transferTo(newfile);

            //图片上传成功，将新图片地址写入数据库
            actfile.setPath(filePath+newFileName);
            actfile.setName(originalFilename);
            actfile.setCreatetime(new Date());
            actfile.setFormat(format);
            actfile.setSize(size);
        }
        actfile.setActivity(1);
        actfile.setEmployee(1);


        actfileService.insert(actfile);
        return  "redirect:/actfile/getActfileByPage.action";
    }

    @RequestMapping("jumpToEdit")
    public String jumpToEdit(Integer uuid,Model model) throws Exception
    {
        Actfile actfile=actfileService.findById(uuid);
        model.addAttribute("actfile",actfile);
        return "leftBox/edit/editActfileInfo";
    }

    @RequestMapping("update")
    public String updata(Integer uuid,Actfile actfile) throws Exception
    {
        actfile.setUuid(uuid);
        actfileService.update(actfile);
        return "redirect:/actfile/getActfileByPage.action";
    }

   /* @RequestMapping("/query")
    public String query(Integer queryAcount1,Integer queryAcount2,Model model) throws Exception
    {
        ActfileQueryModel actfileQueryModel=new ActfileQueryModel();
        actfileQueryModel.setQueryAcount1(queryAcount1);
        actfileQueryModel.setQueryAcount2(queryAcount2);
        PageBean<ActfileQueryModel> pageBeanQuery= new PageBean<ActfileQueryModel>();

        HashMap<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("actfileQuery",actfileQueryModel);

        pageBeanQuery.setParaMap(paraMap);
        List<Actfile> actfiles=actfileService.findByPageQuery(pageBeanQuery);

        PageBean<Actfile> actfilePageBean= new PageBean<Actfile>();
        actfilePageBean.setDatas(actfiles);

        model.addAttribute("actfilePageBean",actfilePageBean);
        return "leftBox/actfileInfo";
    }*/


    @RequestMapping("deletes")
    public  String deletes(String dels) throws Exception
    {

        String str[] = dels.split(",");
        Integer[] delitems=new Integer[str.length];
        for(int i=0;i<str.length;i++){
            delitems[i]=Integer.parseInt(str[i]);
        }



        actfileService.delete(delitems);
        return "redirect:/actfile/getActfileByPage.action";
    }


    @RequestMapping("findone")
    public  String findone(Integer id,Model model) throws  Exception
    {
        id=6;
        Actfile actfile=actfileService.findById(id);
        model.addAttribute("actfile",actfile);
        return "test";
    }

}
