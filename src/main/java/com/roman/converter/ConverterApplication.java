package com.roman.converter;

import com.roman.converter.service.RomanNumberService;
import com.roman.converter.service.RomanNumberServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

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
