package com.example.bookshelter.config;

import com.example.bookshelter.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationSpringClass {

    @Bean
    public Book book() {
        return new Book();
    }
}
