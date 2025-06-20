package dev.razafindratelo.tapakilaBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import java.awt.image.BufferedImage;

@Configuration
public class BufferImgConfig {

    @Bean
    public HttpMessageConverter<BufferedImage> createBufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
