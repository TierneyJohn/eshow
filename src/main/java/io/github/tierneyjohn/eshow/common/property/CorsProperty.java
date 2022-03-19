package io.github.tierneyjohn.eshow.common.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * CORS 配置文件
 * </p>
 * <p>create file on 2022/3/19</p>
 *
 * @author TierneyJohn
 */
@Setter
@Getter
@ConfigurationProperties("cors")
public class CorsProperty {

    private String[] accessControlAllowOrigins = null;

    private String[] accessControlAllowHeaders = null;

    private String[] accessControlAllowMethods = null;
}
