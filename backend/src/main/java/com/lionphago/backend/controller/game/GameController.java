package com.lionphago.backend.controller.game;

import com.lionphago.backend.common.dto.GameDTO;
import com.lionphago.backend.result.Result;
import com.lionphago.backend.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/game")
@Tag(name = "赛事接口")
public class GameController {
    @Autowired
    private GameService gameService;

    /**
     * 赛事添加接口
     * @param {@code GameDTO gameAddRequest}
     * @return {@code GameDTO}
     */
    @Operation(summary = "赛事添加")
    @PostMapping("/add")
    public Result<GameDTO> gameAdd(@RequestBody GameDTO gameAddRequest) {
        // 判空
        if(!StringUtils.hasText(gameAddRequest.getGameTitle())) {
            return Result.error("赛事名称未指定");
        }

        GameDTO r = gameService.gameAdd(gameAddRequest);
        if (r.getGameId() == null) {
            return Result.error("赛事添加失败。");
        }

        return Result.success(r);
    }
}
