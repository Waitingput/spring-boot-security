package cn.pzhu.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springMVC config
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            //路径映射
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("redirect:/login-view");
                registry.addViewController("/index.html").setViewName("redirect:/login-view");
                registry.addViewController("/login-view").setViewName("login");
                registry.addViewController("/amdin/main.html").setViewName("amdin/main");
                registry.addViewController("/teacher/main.html").setViewName("teacher/main");
                registry.addViewController("/student/main.html").setViewName("student/main");
            }
        };
    return configurer;
    }
}
