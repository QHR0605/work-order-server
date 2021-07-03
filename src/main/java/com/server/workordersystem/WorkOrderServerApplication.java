package com.server.workordersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.server.workordersystem.mapper")
public class WorkOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkOrderServerApplication.class, args);
    }

}
