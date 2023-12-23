package com.runes.commponent.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runes.commponent.security.filter.SecurityLoginFilter;
import com.runes.commponent.security.service.RunesUserDetailsService;
import io.swagger.v3.oas.models.PathItem;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnClass(Authentication.class)
public class SecurityConfig {
    @Resource(name = "runesUserDetailsService")
    private  RunesUserDetailsService runesUserDetailsService;

    @Bean
    public PasswordEncoder BcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityLoginFilter loginFilter(){
        SecurityLoginFilter securityLoginFilter = new SecurityLoginFilter();
        securityLoginFilter.setAuthenticationManager(authenticationManager());
        securityLoginFilter.setFilterProcessesUrl("/login");
        securityLoginFilter.setSecurityContextRepository( new HttpSessionSecurityContextRepository());
        securityLoginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Map<String,Object> responseBody = new HashMap<String,Object>();
            responseBody.put("msg","登录成功");
            responseBody.put("用户信息",authentication.getPrincipal());
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=utf-8");
            String responseJson = new ObjectMapper().writeValueAsString(responseBody);
            response.getWriter().println(responseJson);
        });
        securityLoginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            Map<String,Object> responseBody = new HashMap<String,Object>();
            responseBody.put("msg","登录失败");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            String responseJson = new ObjectMapper().writeValueAsString(responseBody);
            response.getWriter().println(responseJson);
        });
        securityLoginFilter.setRememberMeServices(rememberMeServices());
        return securityLoginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(runesUserDetailsService);
        ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);
        return providerManager;
    }

    @Bean
    public RememberMeServices rememberMeServices(){
        PersistentTokenBasedRememberMeServices key = new PersistentTokenBasedRememberMeServices(
                "key", runesUserDetailsService, new InMemoryTokenRepositoryImpl());
        return key;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests( registry->{
                    registry
                            .requestMatchers("/static/**","/resources/**").permitAll()
                            .requestMatchers("/login/**","/doLogin/**").permitAll()
                            .requestMatchers("/db/**").hasRole("dba")
                            .anyRequest().authenticated();
                })
                .exceptionHandling( exceptionConfigurer->{
                    exceptionConfigurer
                            .authenticationEntryPoint((request, response, authException) -> {
                                String message = authException.getMessage();
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                response.setContentType("application/json;charset=utf-8");
                                response.getWriter().println("未认证，请先进行认证");
                            });
                })
                .logout((httpSecurityLogoutConfigurer) -> {
                    httpSecurityLogoutConfigurer
                            .logoutRequestMatcher(new OrRequestMatcher(
                                    new AntPathRequestMatcher("/logout", PathItem.HttpMethod.DELETE.name()),
                                    new AntPathRequestMatcher("/logout", PathItem.HttpMethod.PUT.name()),
                                    new AntPathRequestMatcher("/logout", PathItem.HttpMethod.GET.name())
                            ))
                            .logoutSuccessHandler((request, response, authentication) -> {
                                Map<String,Object> responseBody = new HashMap<String,Object>();
                                responseBody.put("msg","注销成功");
                                responseBody.put("用户信息",authentication.getPrincipal());
                                response.setStatus(HttpStatus.OK.value());
                                response.setContentType("application/json;charset=utf-8");
                                String responseJson = new ObjectMapper().writeValueAsString(responseBody);
                                response.getWriter().println(responseJson);
                            });
                })
                .rememberMe(config->{
                    config.alwaysRemember(true)
                            .key("key");
                })
                .authenticationProvider(new RememberMeAuthenticationProvider("key"))
                .csrf(  csrfConfigurer->{
                    csrfConfigurer
                            .disable();
                })
                .addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
