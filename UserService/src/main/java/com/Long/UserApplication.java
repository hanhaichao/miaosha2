package com.Long;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : UserApplication
      * @date : 2022/2/8 10:27
      */

@EnableDubbo(scanBasePackages = "com.Long")
@SpringBootApplication
@MapperScan(basePackages = "com.Long.dao")
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserApplication.class, args);
    }
}
