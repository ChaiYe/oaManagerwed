package com.officeAuto.ssm.utils;

import com.officeAuto.ssm.model.JobQueryModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Helper {

    public final int pageSize = 5;

    //服务器中存放文件的路径
    public final String rootPath = "D:\\OAstorage\\";
    //可上传文件的最大值
    public final int maxFileSize = 32505856;

    /**
     * 去除重复的部门
     * 遍历求出员工所在所有部门的最高权限职位,并且都大于1
     * 得到的结果存在list中
     * @param jobs
     * @param list
     */
    public static void jobsOption(List<JobQueryModel> jobs, List<JobQueryModel> list){
        for(JobQueryModel job : jobs){
            boolean repeat = false;
            if(job.getAuthority() > 1){
                //遍历已加入的部门
                for(JobQueryModel j : list){
                    //相同部门，取权限高的
                    if(job.getDept().equals(j.getDept())){
                        repeat = true;
                        //取权限高的
                        if(job.getAuthority() > j.getAuthority()) j = job;
                    }
                }
                if(!repeat) list.add(job);
            }
        }
    }

    public static List<JobQueryModel> jobNoRepeat(List<JobQueryModel> jobs){
        List<JobQueryModel> list = new ArrayList<>();
        for(JobQueryModel job : jobs){
            boolean repeat = false;

            //遍历已加入的部门
            for(JobQueryModel j : list){
                //相同部门，取权限高的
                if(job.getDept().equals(j.getDept())){
                    repeat = true;
                    //取权限高的
                    if(job.getAuthority() > j.getAuthority()) j = job;
                }
            }
            if(!repeat) list.add(job);
        }
        return list;
    }


    public static Date convert(String source) {
        String pattern = source.length()==10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
