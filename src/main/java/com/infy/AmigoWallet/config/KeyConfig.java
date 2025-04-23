package com.infy.AmigoWallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@Configuration
public class KeyConfig {
    @Bean
    public SecretKey secretKey() throws NoSuchAlgorithmException {
        KeyGenerator k = KeyGenerator.getInstance("HmacSHA256");
        k.init(512);
        return k.generateKey();
    }
}
