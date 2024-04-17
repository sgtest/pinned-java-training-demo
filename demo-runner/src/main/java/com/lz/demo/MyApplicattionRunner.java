package com.lz.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author lizhi
 * @create 2023-11-01
 **/
@Component
@Order(98)
public class MyApplicattionRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //获取没有键的参数，获取到的值和 CommandLineRunner 一致
        List<String> nonOptionArgs =args.getNonOptionArgs();
        System.out.println("nonOptionArgs = " + nonOptionArgs);

        //获取含有键值对的参数
        Set<String> optionNames=args.getOptionNames();
        for (String optionName:optionNames){
            System.out.println("optionName = "+optionName+" , value =" + args.getOptionValues(optionName));
        }

        //获取命令行中所有参数
        String[] sourceArgs=args.getSourceArgs();
        System.out.println("sourceArgs = " + Arrays.toString(sourceArgs));
    }
}
