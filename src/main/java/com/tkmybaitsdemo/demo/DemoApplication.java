package com.tkmybaitsdemo.demo;

import com.tkmybaitsdemo.demo.model.ClassificationModel;
import com.tkmybaitsdemo.demo.util.SnowflakeIdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.InputStream;

@MapperScan(basePackages = {"com.tkmybaitsdemo.demo.mapper"})
@SpringBootApplication
@Configuration
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator(){
        return new SnowflakeIdGenerator(1,1);
    }

    @Bean
    public InputStream xgbClassifierInputStream()  {
        return getClass().getResourceAsStream("/decision_tree4.pmml");
    }
}
