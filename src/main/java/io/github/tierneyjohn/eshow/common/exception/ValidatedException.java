package io.github.tierneyjohn.eshow.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * <p>
 * 前端输入字段异常信息封装类
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Getter
@RequiredArgsConstructor
public class ValidatedException extends RuntimeException {

    private final List<ObjectError> errors;

}
