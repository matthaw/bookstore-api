package com.restapi.bookstore;

import com.restapi.bookstore.config.FileStorageConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageConfig.class
})
@OpenAPIDefinition
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);

        //        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        //        String result = bCryptPasswordEncoder.encode("admin123");
        //        System.out.println(result);
    }
}
