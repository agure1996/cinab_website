package com.gure.cinab.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinabConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
