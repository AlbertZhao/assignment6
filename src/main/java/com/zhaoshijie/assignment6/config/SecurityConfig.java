package com.zhaoshijie.assignment6.config;

import com.zhaoshijie.assignment6.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String USER_LOGIN_URL = "/assignment/security/login";
    private final static String USER_REGISTER_URL = "/assignment/users/register";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private LoginAuthticationProvider loginAuthticationProvider;

    @Autowired private LoginAuthenticationSuccessHandler successHandler;
    @Autowired private LoginAuthenticationFailureHandler failureHandler;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable().headers().frameOptions().sameOrigin()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .addFilterBefore(buildLoginAuthenticationFilter(USER_LOGIN_URL), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthticationProvider);
    }


    protected LoginAuthenticationFilter buildLoginAuthenticationFilter(String defaultProcessUrl) {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(defaultProcessUrl, successHandler, failureHandler);
        loginAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return loginAuthenticationFilter;
    }
}
