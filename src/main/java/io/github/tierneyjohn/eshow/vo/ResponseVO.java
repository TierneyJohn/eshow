package io.github.tierneyjohn.eshow.vo;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * http 返回结果封装
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@Data
public class ResponseVO {

    /**
     * 返回结果状态
     */
    private String status;

    /**
     * 返回结果数据
     */
    private Object result;

    /**
     * 异常信息封装
     */
    private List<String> errors;
}
