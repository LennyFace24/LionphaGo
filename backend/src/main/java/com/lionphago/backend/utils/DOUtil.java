package com.lionphago.backend.utils;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class DOUtil {
    /**
     * 转换DTO为VO
     * @param {@code UserDTO dto}
     * @return {@code UserVO}
     */
    public static UserVO dtoToVo(UserDTO dto) {
        return UserVO.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .grade(dto.getGrade())
                .classNumber(dto.getClassNumber())
                .major(dto.getMajor())
                .school(dto.getSchool())
                .build();
    }

    /**
     * 验证用户id与请求id一致
     * @param {@code Long userId} {@code Authentication authentication}
     * @return {@code boolean}
     */
    public static boolean verifyIdentity(Long userId, Authentication authentication){
        Long currentUserId = Long.parseLong(authentication.getName());

        // 检查当前登录用户ID和请求修改的用户ID是否一致
        if (!currentUserId.equals(userId)) {
            return false;
        }
        return true;
    }
}
