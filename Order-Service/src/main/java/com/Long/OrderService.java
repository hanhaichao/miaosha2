package com.Long;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : OrderService
      * @date : 2022/2/8 10:32
      * @description: OrderService主启动类
      */

@EnableDubbo(scanBasePackages = "com.Long")
@SpringBootApplication
@MapperScan(basePackages = "com.Long.dao")
public class OrderService {
    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }
}
