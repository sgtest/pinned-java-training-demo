package com.lz.demo;

/**
 * @author lizhi
 * @create 2023-12-04
 **/
public class Test {

    public static void main(String[] args) {
        String a="0";
        Test test=new Test();
        test.setString(a);
        System.out.println("a="+a);
    }

    public void setString(String a){
        a="111";
    }
}
