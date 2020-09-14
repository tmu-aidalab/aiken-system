package com.aiken.bibpaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ログインページを指定。
        // ログインページへのアクセスは全員許可する。
        http.formLogin().loginPage("/login").loginProcessingUrl("/authenticate").usernameParameter("email")
                .passwordParameter("password").defaultSuccessUrl("/").permitAll();

        http.authorizeRequests().antMatchers("/RegistrationForm").permitAll().antMatchers("/Register").permitAll()
                .antMatchers("/Result").permitAll().antMatchers("/ResetPassword").permitAll().antMatchers("/Reset")
                .permitAll().antMatchers("/js/**").permitAll().antMatchers("/css/**").permitAll()
                .antMatchers("/static/**").permitAll().anyRequest().authenticated();

        http.logout().logoutSuccessUrl("/login").permitAll();

        // h2データベースの確認用
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}