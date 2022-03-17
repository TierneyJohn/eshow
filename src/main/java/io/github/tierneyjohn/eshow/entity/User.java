package io.github.tierneyjohn.eshow.entity;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 * <p>
 * 用户实体类
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Data
public class User {

    /**
     * 文档 id
     */
    private ObjectId id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
