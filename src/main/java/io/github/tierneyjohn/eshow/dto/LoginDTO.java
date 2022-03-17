package io.github.tierneyjohn.eshow.dto;

import io.github.tierneyjohn.eshow.common.LoginType;
import lombok.Data;

/**
 * <p>
 * 登录信息 DTO
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Data
public class LoginDTO {

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 登录方式
     */
    private LoginType loginType;
}
