package io.github.tierneyjohn.eshow.vo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * http 响应返回结果封装
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Data
@Builder
public class ResponseVO {

    /**
     * 返回结果状态
     */
    private String status;

    /**
     * 相应状态
     */
    private Boolean success;

    /**
     * 返回结果数据
     */
    private Object result;

    /**
     * 异常信息封装
     */
    private List<String> errors;

    /**
     * 响应时间
     */
    private LocalDateTime timestamp;

    @Contract("_ -> new")
    public static @NotNull ResponseVO buildStringResult(String result) {
        return new ResponseVO("SUCCESS", true, result, null, LocalDateTime.now());
    }
}
