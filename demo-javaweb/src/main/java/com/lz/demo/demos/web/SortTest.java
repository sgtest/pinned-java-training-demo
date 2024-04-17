package com.lz.demo.demos.web;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lizhi
 * @create 2024-03-12
 * 排序测试
 **/
public class SortTest {

    public static void main(String[] args) {

        SortTest test = new SortTest();
        File file = new File("D:\\WorkProject\\downloadFTP\\agentcom\\20240312");

        List<File> files = test.sortFiles(file);
        if (null != files && files.size() > 0) {
            for (int i = 0; i < files.size(); i++) {
                if (!files.get(i).getName().startsWith("UPDATE")) {
                    System.out.println(files.get(i).getName() + "格式不以'UPDATE'开头，跳过");
                    continue;
                }
                System.out.println("准备对文件" + files.get(i).getName() + "进行保单状态更新操作");
                File tFile = files.get(i);
                if (tFile.exists() && tFile.length() == 0) {
                    System.out.println(files.get(i).getName() + "是空文件，跳过");
                }

                System.out.println("执行文件："+files.get(i).getName());
                System.out.println(files.get(i).getName()+"文件执行结束");
            }
        }
    }

    public List sortFiles(File file) {
        List<File> files = Arrays.stream(file.listFiles())
                //过滤非UPDATE文件
                .filter(file1 -> {
                    if (file1.isFile() && file1.getName().startsWith("UPDATE")) {
                        return true;
                    }
                    System.out.println(file1.getName() + "格式不以'UPDATE'开头，跳过");
                    return false;
                })
                //根据保全文件日期进行排序
                .sorted((o1, o2) -> {
                    String o1Name = o1.getName();
                    String o2Name = o2.getName();

                    if (o1Name.length() > 8) {
                        o1Name = o1Name.substring(o1Name.length() - 8, o1Name.length());
                    }
                    if (o2Name.length() > 8) {
                        o2Name = o2Name.substring(o2Name.length() - 8, o2Name.length());
                    }
                    return o1Name.compareTo(o2Name);
                }).collect(Collectors.toList());
        return files;
    }
}

