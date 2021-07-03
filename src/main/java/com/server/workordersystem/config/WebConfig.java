//package com.server.workordersystem.config;
//
//import com.server.workordersystem.mapper.AdminMapper;
//import com.server.workordersystem.util.interceptor.WebInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author 全鸿润
// */
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Bean
//    public HandlerInterceptor getInterceptor() {
//        return new WebInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
//    }
//}
