package com.example.config;

import com.example.rest.SuccessLoginHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 *
 * @author andrey
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SuccessLoginHandler successLoginHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/api/login")
                .successHandler(successLoginHandler)
                .failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) -> {
                    response.setHeader("Content-Type", "application/json");
                    response.getOutputStream().println("{}");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
                .and().logout().logoutUrl("/api/logout").logoutSuccessHandler((HttpServletRequest request, HttpServletResponse response, Authentication a) -> {
                        response.setHeader("Content-Type", "application/json");
                        response.getOutputStream().println("{}");
                        response.setStatus(HttpServletResponse.SC_OK);
                    })
                .and().exceptionHandling().authenticationEntryPoint((HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
                .and().authorizeRequests()
                .antMatchers("/api/signup").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }
    
}
