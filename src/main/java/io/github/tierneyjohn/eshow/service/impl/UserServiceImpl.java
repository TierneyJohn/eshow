package io.github.tierneyjohn.eshow.service.impl;

import io.github.tierneyjohn.eshow.common.LoginType;
import io.github.tierneyjohn.eshow.common.exception.UserException;
import io.github.tierneyjohn.eshow.dto.LoginDTO;
import io.github.tierneyjohn.eshow.entity.User;
import io.github.tierneyjohn.eshow.repository.UserRepository;
import io.github.tierneyjohn.eshow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户信息服务接口实现类
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User login(@NotNull LoginDTO validator) {
        // 添加登录类型校验
        if (Objects.isNull(validator.getLoginType())) {
            validator.setLoginType(LoginType.USERNAME);
        }
        // 根据登录类型执行查询方法
        User user = switch (validator.getLoginType()) {
            case USERNAME -> userRepository.findOneByUsername(validator.getUsername());
            case PHONE -> userRepository.findOneByPhone(validator.getPhone());
            case EMAIL -> userRepository.findOneByEmail(validator.getEmail());
        };
        // 用户信息验证
        if (Objects.isNull(user)) {
            log.warn("用户登录请求: ==> 登录失败,该用户不存在");
            throw new UserException("该用户不存在");
        }
        // 用户密码校验
        if (!Objects.equals(validator.getPassword(), user.getPassword())) {
            log.warn("用户登录请求: ==> 登录失败,登录密码错误");
            throw new UserException("登录密码错误");
        }

        return user;
    }
}
