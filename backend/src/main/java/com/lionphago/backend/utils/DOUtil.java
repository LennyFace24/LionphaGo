package com.lionphago.backend.utils;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;

public class DOUtil {
    public static UserVO dtoToVo(UserDTO dto) {
        return UserVO.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .grade(dto.getGrade())
                .classNumber(dto.getClassNumber())
                .major(dto.getMajor())
                .school(dto.getSchool())
                .roleName(dto.getRoleName())
                .build();
    }
}
