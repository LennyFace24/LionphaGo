package com.lionphago.backend.service;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;

public interface UserLoginService {
    UserVO login(UserDTO userDTO);
}
