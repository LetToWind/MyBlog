package com.ltw.user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(new User("root",new BCryptPasswordEncoder().encode("root"), Collections.singleton(new SimpleGrantedAuthority("root"))));
        return manager;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http
                // 基于 token，不需要 csrf
                .csrf(AbstractHttpConfigurer::disable)
                // 开启跨域以便前端调用接口
                .cors(cors->cors.configurationSource(apiConfigurationSource()))
                .authorizeHttpRequests(a ->a// 指定某些接口不需要通过验证即可访问。登录接口肯定是不需要认证的
                        .requestMatchers("/admin/system/index/login").permitAll()
                        // 静态资源，可匿名访问
                        .requestMatchers(HttpMethod.GET, "/", "/*.html",
                                "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()

                        .requestMatchers("/swagger-ui.html", "/swagger-resources/**",
                                "/webjars/**", "/*/api-docs", "/druid/**", "/doc.html").permitAll()
                        // 这里意思是其它所有接口需要认证才能访问
                        .anyRequest().authenticated())
                //不要session
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * 不会配，全部放行
     * @return
     */
    UrlBasedCorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
