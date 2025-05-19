package com.example.gastroValenciaApi.config;

import com.example.gastroValenciaApi.filters.FirebaseTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Filtro de Firebase
    @Bean
    public FilterRegistrationBean<FirebaseTokenFilter> firebaseFilter() {
        var registrationBean = new FilterRegistrationBean<FirebaseTokenFilter>();
        registrationBean.setFilter(new FirebaseTokenFilter());
        registrationBean.addUrlPatterns("/users/*", "/restaurants/*", "/events/*", "/event_likes/*", "/restaurant_likes/*", "/membership-levels/*", "/discounts/*"
        );
        return registrationBean;
    }

    // Mapeo global de CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                     // Para todas las rutas
                .allowedOrigins("http://localhost:5000") // Mi panel admin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*").allowCredentials(false);
    }
}