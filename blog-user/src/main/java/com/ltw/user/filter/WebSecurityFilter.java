package com.ltw.user.filter;

import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class WebSecurityFilter extends OncePerRequestFilter {

    @Value("${security.jwt.key:aaaabbbbbccccddddddeeeefffffggggg}")
    private String key;
    /**
     * 逻辑上，如果token为空，则检查是否登录，未登录则不放行，已经登录则添加token放行
     * token不为空，未超时，则放行，且可以在此为token延期，若超时，不放行
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {

        }
    }
}
