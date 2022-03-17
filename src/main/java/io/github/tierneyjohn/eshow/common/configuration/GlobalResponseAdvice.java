package io.github.tierneyjohn.eshow.common.configuration;

import io.github.tierneyjohn.eshow.vo.ResponseVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;

/**
 * <p>
 * 全局响应结果处理器
 * </p>
 * <p>create file on 2022/3/17</p>
 *
 * @author TierneyJohn
 */
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(@NotNull MethodParameter returnType,
                            @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType == MappingJackson2HttpMessageConverter.class;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter returnType,
                                  @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        if (body instanceof ResponseVO) {
            return body;
        } else {
            return ResponseVO.builder()
                    .status("SUCCESS")
                    .success(true)
                    .result(body)
                    .errors(null)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
    }
}
