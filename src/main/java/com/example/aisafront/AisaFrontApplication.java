package com.example.aisafront;

import com.example.aisafront.config.AisaFrontConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AisaFrontConfig.class)
public class AisaFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(AisaFrontApplication.class, args);
    }

}
