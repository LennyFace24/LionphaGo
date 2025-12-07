package com.lionphago.backend.service.impl;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserEditService;
import com.lionphago.backend.utils.DOUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserEditServiceImpl implements UserEditService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户修改
     * @param {@code user}
     * @return @{code UserVO}
     */
    public UserVO Edit(UserDTO user) {
        // 从SecurityContext获取当前登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = Long.parseLong(authentication.getName());

        // 检查当前登录用户ID和请求修改的用户ID是否一致
        if (!currentUserId.equals(user.getUserId())) {
            log.error("权限校验失败：用户 {} 尝试修改用户 {} 的信息。", currentUserId, user.getUserId());
            throw new AccessDeniedException("没有权限修改其他用户的信息。");
        }

        // 偷懒，用修改部分替换已有的部分，然后使用整个对象update
        UserDTO userFromDatabase = userMapper.selectById(user.getUserId());

        // 要改的项目就改一下
        if(!user.getUsername().isEmpty()) {
            userFromDatabase.setUsername(user.getUsername());
        }
        if(!user.getPassword().isEmpty()) {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            userFromDatabase.setPassword(encryptedPassword);
        }
        if(user.getGrade().describeConstable().isPresent()) {
            userFromDatabase.setGrade(user.getGrade());
        }
        if(user.getClassNumber().describeConstable().isPresent()) {
            userFromDatabase.setClassNumber(user.getClassNumber());
        }
        if(!user.getMajor().isBlank()) {
            userFromDatabase.setMajor(user.getMajor());
        }
        if(!user.getSchool().isBlank()) {
            userFromDatabase.setSchool(user.getSchool());
        }

        // 写入数据库
        int r = userMapper.updateById(userFromDatabase);

        if(r != 1) {
            log.error("编辑用户 {} 数据库写入失败，写入 {} 行", user.getUserId(), r);
            return UserVO.builder().build();
        }

        return DOUtil.dtoToVo(userFromDatabase);
    }
}
