package io.github.tierneyjohn.eshow.common.configuration;

import io.github.tierneyjohn.eshow.common.exception.BaseException;
import io.github.tierneyjohn.eshow.common.exception.UserException;
import io.github.tierneyjohn.eshow.common.exception.ValidatedException;
import io.github.tierneyjohn.eshow.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * <p>
 * 全局异常信息处理器
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException.class)
    public ResponseVO baseException(@NotNull BaseException error) {
        ResponseVO.ResponseVOBuilder builder = ResponseVO.builder()
                .success(false)
                .result(null)
                .errors(Collections.singletonList(error.getMessage()))
                .timestamp(LocalDateTime.now());

        if (error instanceof UserException) {
            return builder.status("USER_ERROR").build();
        }
        return builder.status("BUSINESS_ERROR").build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidatedException.class)
    public ResponseVO validatedException(@NotNull ValidatedException error) {
        error.getErrors().forEach(e -> log.warn("参数校验异常: ==> {}", e.getDefaultMessage()));
        return ResponseVO.builder()
                .status("VALIDATED_ERROR")
                .success(false)
                .result(null)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
