package com.dash.pro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

@EnableWebSecurity
public class SecureConfg extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("userPassword")
                ./*roles*/authorities("userRole") //если пишем roles то в настройках пишем hasRole/hasAnyRoles
                .and()
                .withUser("admin")
                ./*roles*/authorities("adminRole") //если пишем authorities то в настройках пишем hasAuthority
                .password("adminpassword");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/dash/all").permitAll()
                .antMatchers("/dash/user").hasAnyAuthority("userRole","adminRole")
                /*hasAnyRole если в настройках писали roles*/
                .antMatchers("/dash/admin").hasAuthority("adminRole")
                /*hasRole если в настройках писали roles*/
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
