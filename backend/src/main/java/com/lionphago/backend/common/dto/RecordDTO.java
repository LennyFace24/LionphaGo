package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * 获奖记录
 * <li>{@code gameId} 赛事id</li>
 * <li>{@code gamerId} 参赛者学号</li>
 * <li>{@code awards} 奖项</li>
 */
@Data
@Builder
@TableName("`record`")
public class RecordDTO {
    private Long gameId; // 赛事id
    private Long gamerId; // 获奖者学号
    private Set<String> awards; // 奖项
}
