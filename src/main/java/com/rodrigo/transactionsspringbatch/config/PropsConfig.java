package com.rodrigo.transactionsspringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer config() {  // tirando o application.properties do projeto e deixando no servidor
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("/etc/config/primeirojobspringbatch/application.properties"));
        return configurer;
    }

    // sudo mkdir -p /etc/config/primeirojobspringbatch/
    // sudo nano /etc/config/primeirojobspringbatch/application.properties
}
