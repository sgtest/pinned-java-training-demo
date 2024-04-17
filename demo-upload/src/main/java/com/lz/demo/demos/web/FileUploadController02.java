package com.lz.demo.demos.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author lizhi
 * @create 2023-10-16
 **/
@RestController
public class FileUploadController02 {

    SimpleDateFormat sdf=new SimpleDateFormat("/yyyy-MM-dd/");

    @RequestMapping("/upload02")
    public void upload02(MultipartFile[] files, HttpServletRequest request){
        //获取当前项目路径
        String realPath=request.getServletContext().getRealPath("/");
        System.out.println("realPath = " + realPath);
        String format=sdf.format(new Date());
        String path=realPath+format;

        //创建文件夹
        File pathFile=new File(path);
        if (!pathFile.exists()){
            pathFile.mkdirs();
        }

        try {
            for (MultipartFile file:files){
                String fileName=file.getOriginalFilename();
                String newFileName= UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
                //MultipartFile.transferTo()方法参考：https://blog.csdn.net/lezeqe/article/details/108937647
                file.transferTo(new File(pathFile,newFileName));
                String s=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+format+newFileName;
                System.out.println("s = " + s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
