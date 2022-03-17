package io.github.tierneyjohn.eshow.controller;

import io.github.tierneyjohn.eshow.dto.LoginDTO;
import io.github.tierneyjohn.eshow.entity.User;
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
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户登录方法
     * @param validator 登录信息校验器
     * @return 登录结果
     */
    @PostMapping("/login")
    public User login(@RequestBody @Validated LoginDTO validator) {
        return null;
    }
}
