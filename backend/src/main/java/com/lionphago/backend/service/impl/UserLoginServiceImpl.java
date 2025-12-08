package com.lionphago.backend.service.impl;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserLoginService;
import com.lionphago.backend.utils.DOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager; // 注入 AuthenticationManager

    @Override
    /**
     * 用户登录
     * @param {@code UserDTO userLoginRequest}
     * @return {@code UserVO}
     */
    public UserVO login(UserDTO userLoginRequest) {
        // 使用 AuthenticationManager 进行认证
        // principal 可以是用户名或学号
        String principal = userLoginRequest.getUserId() != null ? userLoginRequest.getUserId().toString() : userLoginRequest.getUsername();
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                principal,
                userLoginRequest.getPassword()
        );
        
        // authenticationManager 认证，配置于 SecurityConfig
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 认证成功后，将认证信息存入 SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 从数据库获取完整的用户信息，返回给前端
        Long userId = Long.parseLong(authentication.getName());
        UserDTO userFromDatabase = userMapper.selectById(userId);
        
        return DOUtil.dtoToVo(userFromDatabase);
    }
}
