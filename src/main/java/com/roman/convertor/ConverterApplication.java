package com.roman.convertor;

import com.roman.convertor.service.RomanNumberService;
import com.roman.convertor.service.RomanNumberServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConverterApplication {

    @Bean
    public RomanNumberService getRomanNumberService() {
        return new RomanNumberServiceImp();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

}
