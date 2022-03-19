package io.github.tierneyjohn.eshow.common.configuration;

import io.github.tierneyjohn.eshow.common.filter.CorsFilter;
import io.github.tierneyjohn.eshow.common.property.CorsProperty;
import org.junit.jupiter.api.Order;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * CORS 处理配置类
 * </p>
 * <p>create file on 2022/3/19</p>
 *
 * @author TierneyJohn
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CorsProperty.class})
public class GlobalCorsConfiguration implements WebMvcConfigurer {

    private final CorsProperty corsProperty;

    public GlobalCorsConfiguration(CorsProperty corsProperty) {
        this.corsProperty = corsProperty;
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsFilter(
                corsProperty.getAccessControlAllowOrigins(),
                corsProperty.getAccessControlAllowHeaders(),
                corsProperty.getAccessControlAllowMethods()));
        return bean;
    }
}
