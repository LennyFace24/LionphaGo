package com.lionphago.backend.controller.user;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.result.Result;
import com.lionphago.backend.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户相关接口")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 用户登录接口
     * @param userDTO
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result userLogin(@RequestBody UserDTO userDTO){
        log.info(userDTO.toString());
        UserVO userVO = userLoginService.login(userDTO);
        return Result.success(userVO);
    }
}
