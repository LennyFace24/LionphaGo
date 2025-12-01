package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.constant.RolenameConstant;
import com.lionphago.backend.exception.UserAlreadyExsistException;
import com.lionphago.backend.exception.UserInfoInvalidException;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserRegisterService;
import com.lionphago.backend.utils.ShiroSHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {


    @Autowired
    private UserMapper userMapper;


    // 学号正则规则
    private static final Pattern USER_ID_PATTERN =
            Pattern.compile("^20\\d{2}[0-9]{2}[0-9]{2}\\d{2}$");

    /**
     * 用户注册
     * @param {@code UserDTO}
     * @return {@code UserVO}
     */
    public UserVO Register(UserDTO userDTO) {

        // 正则检验学号格式
        String userIdStr = String.valueOf(userDTO.getUserId());
        if (!USER_ID_PATTERN.matcher(userIdStr).matches()) {
            throw new UserInfoInvalidException("学号格式不正确，应满足：年份20xx + 学院号01 + 班级号01 + 未知01");
        }

        // 设置身份
        // userDTO.setRoleName();
        QueryWrapper<UserDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userDTO.getUserId())
                .or()
                .eq("username",userDTO.getUsername());
        // 如果存在该学号对应的用户 或 存在相同用户名
        if(userMapper.selectOne(queryWrapper) != null){
            throw new UserAlreadyExsistException("已经存在对应的用户");
        }
        // SHA512 HASH
        userDTO.setPassword(ShiroSHAUtil.encrypt(userDTO.getPassword()));
        userDTO.setRoleName(List.of(RolenameConstant.STUDENT));

        userMapper.insert(userDTO);
        return UserVO.builder().id(userDTO.getUserId())
                .grade(userDTO.getGrade())
                .major(userDTO.getMajor())
                .classNumber(userDTO.getClassNumber())
                .school(userDTO.getSchool())
                .username(userDTO.getUsername())
                .build();
    }
}
