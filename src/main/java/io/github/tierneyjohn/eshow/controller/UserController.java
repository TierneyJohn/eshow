package io.github.tierneyjohn.eshow.controller;

import com.alibaba.fastjson.JSON;
import io.github.tierneyjohn.eshow.common.exception.ValidatedException;
import io.github.tierneyjohn.eshow.dto.LoginDTO;
import io.github.tierneyjohn.eshow.dto.RegisterDTO;
import io.github.tierneyjohn.eshow.entity.User;
import io.github.tierneyjohn.eshow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息控制器
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户登录方法
     * @param validator 登录信息校验器
     * @param errors 异常校验信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public User login(@RequestBody @Validated LoginDTO validator, @NotNull BindingResult errors) {
        log.info("用户登录请求: <== 请求信息: {}", JSON.toJSONString(validator));
        if (errors.getErrorCount() != 0) {
            log.warn("用户登录请求: ==> 登录失败,字段校验失败");
            throw new ValidatedException(errors.getAllErrors());
        }
        log.info("用户登录请求: <== 请求信息校验成功");
        User user = userService.login(validator);
        log.info("用户登录请求: ==> 登录成功,用户 id 为: {}", user.getId());
        return user;
    }

    /**
     * 用户注册方法
     * @param validator 注册信息校验器
     * @param errors 异常校验信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public User register(@RequestBody @Validated RegisterDTO validator, @NotNull BindingResult errors) {
        log.info("用户注册请求: <== 请求信息: {}", JSON.toJSONString(validator));
        if (errors.getErrorCount() != 0) {
            log.warn("用户注册请求: ==> 注册失败,字段校验失败");
            throw new ValidatedException(errors.getAllErrors());
        }
        log.info("用户注册请求: <== 请求信息校验成功");
        User user = userService.register(validator);
        log.info("用户注册请求: ==> 注册成功,用户 id 为: {}", user.getId());
        return user;
    }
}
