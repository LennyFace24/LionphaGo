package com.lionphago.backend.service.impl;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.service.UserLoginService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    public UserVO login(UserDTO userDTO) {
        if(userDTO.getPassword().equals("admin") && userDTO.getUsername().equals("admin")){
            return UserVO.builder().success(true).build();
        }
        return UserVO.builder().success(false).build();
    }
}
