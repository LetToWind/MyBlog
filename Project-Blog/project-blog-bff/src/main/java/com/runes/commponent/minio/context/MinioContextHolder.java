package com.runes.commponent.minio.context;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;


public class MinioContextHolder {
    public final static ThreadLocal<MinioContext> MINIO_CONTEXT_THREAD_LOCAL = new ThreadLocal<>() {
        @Override
        protected MinioContext initialValue() {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if(securityContext != null){
                Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
                Optional<String> roleOption = authorities.stream().filter(authority -> authority.getAuthority().startsWith("ROLE_MINIO_"))
                        .findFirst().map(authority -> authority.getAuthority().replace("ROLE_MINIO_", ""));
                if (roleOption.isPresent()){
                    return MinioContextFactory.getMinioContext(roleOption.get());
                }
            }
            throw new RuntimeException("没有权限");
        }
    };

    public static MinioContext getMinioContext(){
        return MINIO_CONTEXT_THREAD_LOCAL.get();
    }

}
