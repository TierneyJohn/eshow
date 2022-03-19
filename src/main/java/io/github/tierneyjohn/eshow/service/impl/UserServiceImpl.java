package io.github.tierneyjohn.eshow.service.impl;

import io.github.tierneyjohn.eshow.common.enums.LoginType;
import io.github.tierneyjohn.eshow.common.exception.UserException;
import io.github.tierneyjohn.eshow.dto.LoginDTO;
import io.github.tierneyjohn.eshow.dto.RegisterDTO;
import io.github.tierneyjohn.eshow.entity.User;
import io.github.tierneyjohn.eshow.repository.UserRepository;
import io.github.tierneyjohn.eshow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Locale;
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
        // validator 数据处理
        if (Objects.nonNull(validator.getUsername())) {
            validator.setUsername(validator.getUsername().toLowerCase(Locale.ROOT));
        }
        if (Objects.nonNull(validator.getEmail())) {
            validator.setEmail(validator.getEmail().toLowerCase(Locale.ROOT));
        }

        // 添加登录类型校验
        if (Objects.isNull(validator.getLoginType())) {
            validator.setLoginType(LoginType.USERNAME);
        }

        // 根据登录类型执行查询方法
        User user = switch (validator.getLoginType()) {
            case USERNAME -> Objects.nonNull(validator.getUsername()) ? userRepository.findOneByUsername(validator.getUsername()) : null;
            case PHONE -> Objects.nonNull(validator.getPhone()) ? userRepository.findOneByPhone(validator.getPhone()) : null;
            case EMAIL -> Objects.nonNull(validator.getEmail()) ? userRepository.findOneByEmail(validator.getEmail()) : null;
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

    @Override
    public User register(@NotNull RegisterDTO validator) {
        // validator 数据处理
        validator.setUsername(validator.getUsername().toLowerCase(Locale.ROOT));
        validator.setEmail(validator.getEmail().toLowerCase(Locale.ROOT));
        // 获取相应用户信息
        User user = userRepository.findOneByUsername(validator.getUsername());
        // 验证是否存在该用户名
        if (Objects.nonNull(user)) {
            log.warn("用户注册请求: ==> 注册失败,用户名已存在");
            throw new UserException("用户名已存在");
        }

        user = userRepository.findOneByPhone(validator.getPhone());
        // 验证是否存在该手机号
        if (Objects.nonNull(user)) {
            log.warn("用户注册请求: ==> 注册失败,手机号已被注册");
            throw new UserException("手机号已被注册");
        }

        user = userRepository.findOneByEmail(validator.getEmail());
        // 验证是否存在该邮箱地址
        if (Objects.nonNull(user)) {
            log.warn("用户注册请求: ==> 注册失败,该邮箱已被注册");
            throw new UserException("该邮箱已被注册");
        }

        // 创建用户信息实体类
        user = new User();
        BeanUtils.copyProperties(validator, user);
        return userRepository.save(user);
    }
}
