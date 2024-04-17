/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lz.demo.demos.web;

import com.lz.demo.FileCharsetUtils;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    @RequestMapping(value="/charset",method= RequestMethod.POST)
    @ResponseBody
    public String chartSet(String fileName)  {
        try {
            String charset=FileCharsetUtils.getGBK_UTF8(fileName);

            BufferedReader reader=  new BufferedReader(new InputStreamReader(new FileInputStream(fileName),charset));


            String strLine="";
            StringBuffer buffer=new StringBuffer();
            do{
                strLine=reader.readLine();

                buffer.append(strLine).append("/n");

            } while (reader.ready() && strLine!=null);

            reader.close();
            return buffer.toString() +"  "+charset;

        }catch (Exception e){
            e.printStackTrace();
        }

        return "编译失败";
    }


    @RequestMapping(value="/charset2",method= RequestMethod.POST)
    @ResponseBody
    public String charset2(String fileName)  {

        try {
            File file = new File(fileName);
            InputStream in= new java.io.FileInputStream(file);
            byte[] b = new byte[3];
            in.read(b);
            in.close();
            if (b[0] == -17 && b[1] == -69 && b[2] == -65)
                return file.getName() + "：编码为UTF-8";
            else
                return file.getName() + "：可能是GBK，也可能是其他编码";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "编译失败";
    }




    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html(){
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
