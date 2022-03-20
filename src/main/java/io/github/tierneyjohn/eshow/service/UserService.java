package io.github.tierneyjohn.eshow.service;

import io.github.tierneyjohn.eshow.dto.LoginDTO;
import io.github.tierneyjohn.eshow.dto.RegisterDTO;
import org.bson.types.ObjectId;

/**
 * <p>
 * 用户信息服务接口
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
public interface UserService {

    /**
     * 用户登录方法
     * @param validator 登录信息校验器
     * @return 登录用户 id
     */
    ObjectId login(LoginDTO validator);

    /**
     * 用户注册方法
     * @param validator 注册信息校验器
     * @return 注册结果 id
     */
    ObjectId register(RegisterDTO validator);
}
