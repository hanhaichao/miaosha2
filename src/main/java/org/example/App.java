package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//(exclude = DataSourceAutoConfiguration.class)
//去除掉对DruidDataSourceAutoConfigure的自动配置，
@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan("org.example.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
