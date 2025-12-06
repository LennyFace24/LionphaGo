package com.lionphago.backend.utils;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class RoleAuthorityConverter {
    public static List<GrantedAuthority> converter(List<String> roleNames){
        List<GrantedAuthority> authorities = roleNames.stream()
                // 强制要求：Spring Security 推荐角色名以 "ROLE_" 开头
                .map(roleName -> {
                    String authorityName = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;
                    return new SimpleGrantedAuthority(authorityName);
                })
                .collect(Collectors.toList());
        return authorities;
    }
}
