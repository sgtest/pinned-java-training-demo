package com.lz.demo.demos.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lizhi
 * @create 2024-03-12
 **/
public class Dog {
    public int age;
    public String name;
    public Dog(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Dog [age=" + age + ", name=" + name + "]";
    }


    public static void main(String[] args) {
        List<Dog> list= new ArrayList<>();
        list.add(new Dog(6, "DogD"));
        list.add(new Dog(5, "DogA"));
        list.add(new Dog(8, "DogB"));
        list.add(new Dog(8, "DogC"));
        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                int x=(o1.age -o2.age)*-1;
                return  x;
            }
        });
        System.out.println("给狗狗按照年龄倒序："+list);
        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                int i = o1.name.compareTo(o2.name);
                return i;
            }
        });
        System.out.println("给狗狗按名字字母顺序排序："+list);
    }
}
