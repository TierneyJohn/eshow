package io.github.tierneyjohn.eshow.common.exception;

/**
 * <p>
 * 公共异常父类
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }
}
