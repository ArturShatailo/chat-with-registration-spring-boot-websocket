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
//                    .cors().configurationSource(corsConfigurationSource())
//                .and()
                    .csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/api/registration/**", "/register", "/").permitAll()
                    .requestMatchers("/js/**", "/css/**", "/img/public/**").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    //.failureForwardUrl("/login?error")
                    .defaultSuccessUrl("/dashboard", true)
                    //.successForwardUrl("/chat.html")
                .and()
                    .logout().permitAll();

        auth.authenticationProvider(daoAuthenticationProvider());

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
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

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
