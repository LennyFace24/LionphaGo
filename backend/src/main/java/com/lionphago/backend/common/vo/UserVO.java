package com.lionphago.backend.common.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用户VO
 * <li>{@code userId} 学号</li>
 * <li>{@code username} 用户名</li>
 * <li>{@code grade} 年级</li>
 * <li>{@code classNumber} 班级</li>
 * <li>{@code major} 专业</li>
 * <li>{@code school} 学院</li>
 */
@Data
@Builder
public class UserVO {
    private Long id;  // 学号
    private String username; // 用户名
    private Integer grade; // 年级
    private Integer classNumber; // 班级
    private String major; // 专业
    private String school; // 学院
}
