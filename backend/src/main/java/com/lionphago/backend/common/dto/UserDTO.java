package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`user`")
public class UserDTO {
    private Long id;
    private String username;
    private String  password;
}
