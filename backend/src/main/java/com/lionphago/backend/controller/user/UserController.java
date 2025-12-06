package com.lionphago.backend.controller.user;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.result.Result;
import com.lionphago.backend.service.UserLoginService;
import com.lionphago.backend.service.UserRegisterService;
import com.lionphago.backend.service.UserEditService;
import com.lionphago.backend.utils.RoleAuthorityConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户相关接口")
public class UserController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private UserEditService userEditService;

    /**
     * 用户登录接口
     * @params {@code id}, {@code username}, {@code password}
     * @return {@code Result}
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result userLogin(@RequestParam(value = "id", required = false) Long id
            , @RequestParam(value = "username", required = false) String username
            , @RequestParam("password") String password
            , HttpServletRequest request) {
        // 判空
        if (username.isEmpty() && id == null) {
            return Result.error("用户名和学号为空");
        }
        if (password == null) {
            return Result.error("口令为空");
        }

        UserDTO userDTO = UserDTO.builder()
                .userId(id)
                .username(username)
                .password(password)
                .build();
        // 验证
        UserVO userVO = userLoginService.login(userDTO);
        if (userVO.getUserId() == null) {
            return Result.error("账户或密码错误");
        }

        // 构造 Authentication 对象
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userVO.getUserId(),
                null,
                RoleAuthorityConverter.converter(userVO.getRoleName())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 把用户信息放入session
        HttpSession httpSession = request.getSession(true);

        // 把securityContext存到session中 确保spring security识别用户状态
        httpSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );
        httpSession.setAttribute("user",userVO);



        return Result.success(userVO);
    }

    /**
     * 用户注册接口
     * @param {@code userDTO}
     * @return {@code Result}
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result userRegister(@RequestBody UserDTO userDTO) {
        // 判空
        if (StringUtils.hasText(userDTO.getUsername())
                && userDTO.getUserId() != null
                && StringUtils.hasText(userDTO.getPassword())
                && StringUtils.hasText(userDTO.getMajor())
                && StringUtils.hasText(userDTO.getSchool())
                && userDTO.getClassNumber() != null
                && userDTO.getGrade() != null){
            // 注册
            UserVO userVO = userRegisterService.Register(userDTO);
            return Result.success(userVO);
        } else {
            // 若有一项为空 则不通过
            return Result.error("存在未填的信息！");
        }
    }

    /**
     * 用户编辑接口
     * @param {@code userDTO}
     * @return {@code Result}
     */
    @Operation(summary = "用户编辑")
    @PostMapping("/edit")
    public Result userEdit(@RequestBody UserDTO userDTO) {
        // 什么都没改啊
        if(userDTO.getUsername().isEmpty()
                && userDTO.getPassword().isEmpty()
                && userDTO.getGrade().describeConstable().isEmpty()
                && userDTO.getClassNumber().describeConstable().isEmpty()
                && userDTO.getMajor().isBlank()
                && userDTO.getSchool().isBlank()){
            return Result.error("这不是什么都没改吗！");
        }

        /**
         * 主键即学号一定要存在，
         * 若前端省略则从session等持久化区域获取。
         * 这里也不提供自行更改学号的功能，
         * 更改学号的实现应该放在管理侧接口，
         * 或在此接口中额外进行鉴权。
         * 安全考虑，角色同理。
          */
        if(userDTO.getUserId().describeConstable().isEmpty()) {
            return Result.error("缺失学号参数！");
        }

        UserVO userVO = userEditService.Edit(userDTO);

        // 失败了？
        if (userVO.getUserId() == null) {
            return Result.error("编辑用户失败。");
        }
        return Result.success(userVO);
    }
}
