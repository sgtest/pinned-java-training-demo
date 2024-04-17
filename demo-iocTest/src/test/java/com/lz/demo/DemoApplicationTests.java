package com.lz.demo;

import com.lz.demo.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    FileService fileService;
    @Test
    void contextLoads() {
        fileService.getFileMIMEType();
    }

}
