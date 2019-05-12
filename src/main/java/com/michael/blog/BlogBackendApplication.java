package com.michael.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
//@EnableJpaRepositories(basePackages = {"com.michael.blog.repository"})
//@ComponentScan(value = {"com.michael.blog.*"})
//@EntityScan("com.michael.blog.entity")
@SpringBootApplication
public class BlogBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBackendApplication.class, args);
    }

}
