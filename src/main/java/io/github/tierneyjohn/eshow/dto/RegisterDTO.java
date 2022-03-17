package io.github.tierneyjohn.eshow.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户注册信息 DTO
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Data
public class RegisterDTO {

    /**
     * 登录账号
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 20, message = "用户名长度应在4~20位之间")
    private String username;

    /**
     * 登录手机号
     */
    @Length(min = 11, max = 11, message = "手机号位数不正确")
    private String phone;

    /**
     * 登录邮箱号
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
