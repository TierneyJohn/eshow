package io.github.tierneyjohn.eshow.common.filter;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Cors 拦截过滤器
 * </p>
 * <p>create file on 2022/3/19</p>
 *
 * @author TierneyJohn
 */
public class CorsFilter extends OncePerRequestFilter {

    private final String[] accessControlAllowOrigins;
    private final String[] accessControlAllowHeaders;
    private final String[] accessControlAllowMethods;

    public CorsFilter(String[] accessControlAllowOrigins,
                      String[] accessControlAllowHeaders,
                      String[] accessControlAllowMethods) {
        this.accessControlAllowOrigins = accessControlAllowOrigins;
        this.accessControlAllowHeaders = accessControlAllowHeaders;
        this.accessControlAllowMethods = accessControlAllowMethods;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
//        String originHeader = request.getHeader(HttpHeaders.ORIGIN);

        for (String accessControlAllowOrigin : accessControlAllowOrigins) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, accessControlAllowOrigin);
        }

        for (String accessControlAllowHeader : accessControlAllowHeaders) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, accessControlAllowHeader);
        }

        for (String accessControlAllowMethod : accessControlAllowMethods) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, accessControlAllowMethod);
        }

        filterChain.doFilter(request, response);
    }
}
