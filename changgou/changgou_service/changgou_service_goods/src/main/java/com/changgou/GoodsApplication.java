package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"})
// 排除旧练习代码包 com.changgou.service，避免 Bean 重名冲突
@ComponentScan(basePackages = {"com.changgou"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "com\\.changgou\\.service\\..*"))
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run( GoodsApplication.class);
    }
}
