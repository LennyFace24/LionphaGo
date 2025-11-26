package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import java.util.Set;

/**
 * 用户
 * <li>{@code userId} 学号</li>
 * <li>{@code username} 用户名</li>
 * <li>{@code password} 口令</li>
 * <li>{@code grade} 年级</li>
 * <li>{@code _class} 班级</li>
 * <li>{@code major} 专业</li>
 * <li>{@code school} 学院</li>
 * <li>{@code roleName} 角色</li>
 */
@Data
@Builder
@TableName("`user`")
public class UserDTO {
    private Long userId;  // 学号
    private String username; // 用户名
    private String password; // 口令
    private Integer grade; // 年级
    private Integer _class; // 班级
    private String major; // 专业
    private String school; // 学院
    private Set<String> roleName; // 角色
}
