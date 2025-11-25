package com.lionphago.backend.service.impl;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param user
     * @return
     */
    public UserVO login(UserDTO user) {
        UserDTO userFromDatabase = userMapper.selectById(user.getId());
        if (userFromDatabase.getPassword().equals(user.getPassword())) {
            return UserVO.builder()
                    .id(userFromDatabase.getId())
                    .username(userFromDatabase.getUsername())
                    .build();
        }
        return UserVO.builder().build();
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public UserVO register(UserDTO user) {
        return UserVO.builder().build();
    }
}
