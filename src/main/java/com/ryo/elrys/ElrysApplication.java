package com.ryo.elrys;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.ryo.elrys.repository")
@OpenAPIDefinition(info = @Info(title = "Elrys", description = "Just Playground", version = "v1", license = @License(name = " MIT license")))
public class ElrysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElrysApplication.class, args);
    }

}
