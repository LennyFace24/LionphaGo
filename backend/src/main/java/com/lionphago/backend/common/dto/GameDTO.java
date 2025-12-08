package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName(value = "`game`",autoResultMap = true)
public class GameDTO {
    @TableId(value = "game_id")
    private Long gameId; // 赛事id

    @TableField(value = "game_title")
    private String gameTitle; // 赛事名称

    @TableField(value = "gamers")
    private Set<Long> gamers;  // 参赛(学号)名单
}
