package com.runes.commponent.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@Setter
@Getter
public class SecurityLoginFilter extends UsernamePasswordAuthenticationFilter {
    public static final String CAPTCHA = "captcha";
    private String captchaParameter = CAPTCHA;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if(request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)){
            try {
                Map<String,String> userInfo = new ObjectMapper().readValue(request.getInputStream(),Map.class);
//                String captcha = userInfo.get(captchaParameter);
//                String captchaCode =(String) request.getSession().getAttribute("captcha");
//                if(!StringUtils.hasText(captcha) || !StringUtils.hasText(captchaCode) || !captcha.equals(captchaCode)){
//                    throw new AuthenticationCaptchaException("Authentication captcha excepted");
//                }
                String userName = userInfo.get(this.getUsernameParameter());
                userName = (userName != null) ? userName.trim() : "";
                String password = userInfo.get(this.getPasswordParameter());
                password = (password != null) ? password : "";
//                String rememberMeValue = userInfo.get(AbstractRememberMeServices.DEFAULT_PARAMETER);
//                if(StringUtils.hasText(rememberMeValue)){
//                    request.setAttribute(AbstractRememberMeServices.DEFAULT_PARAMETER,rememberMeValue);
//                }
                UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(userName,
                        password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new AuthenticationServiceException("Authentication mediaType not supported: " + request.getContentType());
        }
    }

}
