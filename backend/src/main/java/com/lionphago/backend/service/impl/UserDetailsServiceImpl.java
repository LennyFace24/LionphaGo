package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.utils.RoleAuthorityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    /**
     * 重写 Spring Security 读入用户信息逻辑
     * @param {@code username}
     * @param {@code UserDetails}
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username 参数可以是用户名，也可以是学号的字符串
        QueryWrapper<UserDTO> queryWrapper = new QueryWrapper<>();
        if (username.matches("\\d+")) {
            queryWrapper.eq("user_id", Long.parseLong(username));
        } else {
            queryWrapper.eq("username", username);
        }

        UserDTO userFromDatabase = userMapper.selectOne(queryWrapper);

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("用户 " + username + " 不存在");
        }

        // Spring Security 内部操作，使用 Spring Security 的 User 对象
        return new User(
                userFromDatabase.getUserId().toString(), // principal，只用唯一标识符 userId
                userFromDatabase.getPassword(), // 已加密的密码
                RoleAuthorityConverter.converter(userFromDatabase.getRoleName()) // 权限集合
        );
    }
}