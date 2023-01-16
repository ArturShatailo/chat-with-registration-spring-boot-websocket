package com.websocket.onlinechat.authentification.security.config;

import com.websocket.onlinechat.authentification.chatuser.service.ChatUserServiceBean;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{

    private final ChatUserServiceBean chatUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/chat/user/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin();
        auth
                .authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(chatUserService);
        return provider;
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests((auths) -> {
//                            try {
//                                auths.requestMatchers("/api/chat/user/registration/**")
//                                        .permitAll()
//                                        .anyRequest()
//                                        .authenticated().and()
//                                        .formLogin();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                });
//        return http.build();
//    }

}
