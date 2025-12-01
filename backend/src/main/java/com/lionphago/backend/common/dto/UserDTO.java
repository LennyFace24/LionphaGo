package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lionphago.backend.handler.StringListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * 用户
 * <li>{@code userId} 学号</li>
 * <li>{@code username} 用户名</li>
 * <li>{@code password} 口令</li>
 * <li>{@code grade} 年级</li>
 * <li>{@code classNumber} 班级</li>
 * <li>{@code major} 专业</li>
 * <li>{@code school} 学院</li>
 * <li>{@code roleName} 角色</li>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserDTO {

    @TableId(value = "user_id")
    private Long userId;  // 学号

    private String username; // 用户名

    private String password; // 口令

    private Integer grade; // 年级

    @TableField(value = "class_number")
    private Integer classNumber; // 班级

    private String major; // 专业

    private String school; // 学院

    @TableField(value = "role_name",typeHandler = StringListTypeHandler.class)
    private List<String> roleName; // 角色
}
