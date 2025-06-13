package com.atguigu.spzx.manager;

import com.atguigu.spzx.manager.properties.MinIoProperties;
import com.atguigu.spzx.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//手动扫描swagger配置类，因为swagger配置类不在当前启动类的子包中，所以需要手动扫描
@ComponentScan(basePackages = {"com.atguigu.spzx"})
//注册配置类                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
@EnableConfigurationProperties({UserProperties.class, MinIoProperties.class})
@EnableTransactionManagement
public class ManagerAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(ManagerAppliaction.class,args);
    }
}
