package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserLoginService;
import com.lionphago.backend.utils.DOUtil;
import com.lionphago.backend.utils.ShiroSHAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param {@code UserDTO}
     * @return {@code UserVO}
     */
    public UserVO login(UserDTO user) {
        UserDTO userFromDatabase = null;

        if(user.getUserId() != null) {
            userFromDatabase = userMapper.selectById(user.getUserId());
            log.info(userFromDatabase.toString());
        } else if (!user.getUsername().isEmpty()) {
            QueryWrapper<UserDTO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", user.getUsername());
            userFromDatabase  = userMapper.selectOne(queryWrapper);
        }
        // 数据库中不存在
        if(userFromDatabase == null){
            return UserVO.builder().build();
        }
        // 加密
        String encryptedPassword = ShiroSHAUtil.encrypt(user.getPassword());
        if (userFromDatabase.getPassword().equals(encryptedPassword) ){
            return DOUtil.dtoToVo(userFromDatabase);
        }
        return UserVO.builder().build();
    }
}
