package com.Long;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : promoService
      * @date : 2022/2/8 10:26
      * @description: 秒杀服务启动类
      */

@EnableDubbo(scanBasePackages = "com.Long")
@SpringBootApplication
@MapperScan(basePackages = "com.Long.dao")
public class promoService {
    public static void main(String[] args) {
        SpringApplication.run(promoService.class, args);
    }
}
