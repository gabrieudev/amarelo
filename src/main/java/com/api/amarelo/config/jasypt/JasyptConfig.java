package com.api.amarelo.config.jasypt;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.password}")
    private String jasyptPassword;

    @Bean
    public StrongTextEncryptor strongTextEncryptor() {
        StrongTextEncryptor strongTextEncryptor = new StrongTextEncryptor();
        strongTextEncryptor.setPassword(jasyptPassword);
        return strongTextEncryptor;
    }

}
