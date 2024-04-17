package com.lz.demo.service;

import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

/**
 * @author lizhi
 * @create 2023-12-14
 **/
@Service
public class FileService {

    public void getFileMIMEType(){
        String filepath="D:/WorkProject/downloadFTP/down/generate_code-main.zip";
        File file=new File(filepath);
        String filename = file.getName();
        System.out.println("fileName="+filename);
        Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(filename);
        mediaType.ifPresent(type -> System.out.println("mimeType="+(type.toString())));
    }
}
