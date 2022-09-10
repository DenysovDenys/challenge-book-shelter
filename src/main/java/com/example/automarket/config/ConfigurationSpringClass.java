package com.example.automarket.config;

import com.example.automarket.entity.Car;
import com.example.automarket.entity.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigurationSpringClass implements WebMvcConfigurer {

    @Bean
    public Company company() {
        return new Company();
    }

    @Bean
    public Car car() {
        return new Car();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
