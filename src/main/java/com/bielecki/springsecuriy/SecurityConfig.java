package com.bielecki.springsecuriy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private AlwaysAllowFiler alwaysAllowFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .addFilterAfter(alwaysAllowFilter, BasicAuthenticationFilter.class) //rejestracja mojego filtra (allow..)
                .authorizeRequests().anyRequest()
                .authenticated().and().formLogin();
        //jezeli nie dodamy wlasnego filra (j/w), zostanie uzyty defaultowy: UserNamePasswordAutthFiler
    }

    @Configuration //rejestracja securityContextu
    public static class SecuriyContextHolderConfig {
        @Bean
        public SecurityContextHolderStrategy securityContextHolderStrategy(){
            return SecurityContextHolder.getContextHolderStrategy();
        }
    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new AuthenticationProvider() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return new UsernamePasswordAuthenticationToken("any principal",
//                        "any credentials",
//                        List.of(new SimpleGrantedAuthority("USER")));
//            }
//
//            @Override
//            public boolean supports(Class<?> authentication) {
//                return true;
//            }
//        });
//    }
}
