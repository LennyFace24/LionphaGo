package com.lionphago.backend.service;

import com.lionphago.backend.common.dto.GameDTO;

public interface GameService {
    GameDTO gameAdd(GameDTO gameAddRequest);

    GameDTO gameDel(GameDTO gameDelRequest);

    GameDTO gameEdit(GameDTO gameEditRequest);

    GameDTO gameSign(Long userId);

    GameDTO gameQuit(Long userId);
}
