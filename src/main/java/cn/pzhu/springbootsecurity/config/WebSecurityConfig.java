package cn.pzhu.springbootsecurity.config;

import cn.pzhu.springbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security-config
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    //加密方法
    @Bean
    PasswordEncoder passwordEncoder(){
       // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
       return new BCryptPasswordEncoder();
    }
    //认证规则（数据库认证），指定userService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    //url授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/amdin/**").hasAuthority("amdin")
                .antMatchers("/student/**").hasAuthority("student")
                .antMatchers("/teacher/**").hasAuthority("teacher")
                //不需要授权
                .antMatchers("/druid/**","/asserts/**","/webjars/**","/error/**","/login.html","/","/index.html").permitAll()
                //其他request需要授权
                .anyRequest().authenticated()
                .and()
                //支持表单登录
                .formLogin()
                //自定义登录页
                .loginPage("/login-view").permitAll()
                //登录请求
                .loginProcessingUrl("/user/login").defaultSuccessUrl("/user/redirect")
                .and()
                .csrf().disable()
                //退出登录
                //退出成功后默认session无效
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login-view?logut")
                .and()
                //同账户最大session保持数
                .sessionManagement().maximumSessions(1).expiredUrl("/logout");
    }
}
