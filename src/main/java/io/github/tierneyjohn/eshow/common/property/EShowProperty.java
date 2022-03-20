package io.github.tierneyjohn.eshow.common.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 项目配置
 * </p>
 * <p>create file on 2022/3/19</p>
 *
 * @author TierneyJohn
 */
@Setter
@Getter
@ConfigurationProperties("eshow")
public class EShowProperty {

    private String resourcePath = null;
}
