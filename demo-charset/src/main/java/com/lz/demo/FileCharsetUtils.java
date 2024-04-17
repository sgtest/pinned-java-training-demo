package com.lz.demo;

import info.monitorenter.cpdetector.io.*;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 获取文件的编码
 */
public class FileCharsetUtils {



    public static String getDetectedCharset(String path) throws IOException {
        byte[] buf = new byte[4096];
        java.io.InputStream fis = java.nio.file.Files.newInputStream(java.nio.file.Paths.get(path));

        // (1)
        UniversalDetector detector = new UniversalDetector();

        // (2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        // (3)
        detector.dataEnd();

        // (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
        } else {
            System.out.println("No encoding detected.");
            encoding="空";
        }

        // (5)
        detector.reset();
        return encoding;
    }

    /***
     * 解析文件编码，当编码为utf-8时返回UTF-8,否则返回GBK,无法解析返回null
     * @param path
     * @return UTF-8,GBK,null
     */
    public static String getGBK_UTF8(String path) {
        String charSet="GBK";
        try {

            charSet=getFileEncode(path);
            System.out.println(charSet);
            if (charSet!=null && charSet.toUpperCase().startsWith("UTF")){
                charSet=checkEncode(path);
                charSet=charSet.toUpperCase().startsWith("UTF")?charSet.toUpperCase():"GBK";
                System.out.println(charSet);
            }else if (charSet.toUpperCase().startsWith("UTF")){
                charSet=charSet.toUpperCase();
            }else {
                charSet="GBK";
            }

        }catch (Exception e){
            e.printStackTrace();
            return charSet;
        }
        return charSet;
    }




    /**
     * 使用字节判断是否属于UTF-8等编码
     * @param path 要判断文件编码格式的源文件的路径
     * @author lizhi
     * @version 2024-3-20 14:05
     */
    public static String checkEncode(String path) {
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(false));
        detector.add(new ByteOrderMarkDetector());
        java.nio.charset.Charset charset = null;
        File f = new File(path);
        try {
            InputStream in= new java.io.FileInputStream(f);
            byte[] b = new byte[4];
            in.read(b);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
            ICodepageDetector byteOrderMarkDetector = new ByteOrderMarkDetector();
            charset = byteOrderMarkDetector.detectCodepage(byteIn, 4);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null)
            return charset.name();
        else
            return null;
    }

    /**
     * 利用第三方开源包cpdetector获取文件编码格式
     * @param path
     *            要判断文件编码格式的源文件的路径
     * @author huanglei
     * @version 2012-7-12 14:05
     */
    public static String getFileEncode(String path) {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
         * JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
         * cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        File f = new File(path);
        try {
            charset = detector.detectCodepage(f.toURI().toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null)
            return charset.name();
        else
            return null;
    }


    /**
     * 利用第三方开源包cpdetector获取URL对应的文件编码
     * @param path
     *            要判断文件编码格式的源文件的URL
     * @author huanglei
     * @version 2012-7-12 14:05
     */
    public static String getFileEncode(URL url) {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
         * JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
         * cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        try {
            charset = detector.detectCodepage(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null)
            return charset.name();
        else
            return null;
    }
}
