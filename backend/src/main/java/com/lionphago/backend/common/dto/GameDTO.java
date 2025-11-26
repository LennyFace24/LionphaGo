package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * 赛事
 * <li>{@code gameId} 赛事id</li>
 * <li>{@code gameTitle} 赛事名称</li>
 * <li>{@code gamers} 参赛名单</li>
 */
@Data
@Builder
@TableName("`game`")
public class GameDTO {
    private Long gameId; // 赛事id
    private String gameTitle; // 赛事名称
    private Set<Long> gamers;  // 参赛(学号)名单
}
