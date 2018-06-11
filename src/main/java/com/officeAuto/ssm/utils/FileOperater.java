package com.officeAuto.ssm.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileOperater {
    
    public static void fileUpload(MultipartFile file, String path, String filename) throws IOException {
        
        //文件格式
        String originalFilename = file.getOriginalFilename();
        String format = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        
        //判断路径是否存在，如果不存在就创建一个
        File filepath = new File(path,filename);
        if (!filepath.getParentFile().exists())
            filepath.getParentFile().mkdirs();

        //文件的路径全名
        String longFileName = path + File.separator + filename;
        //将上传文件保存到一个目标文件当中
        file.transferTo(new File(longFileName));
    }
}
