package com.changgou.system.filter;

import com.changgou.system.util.JwtUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求对象
        ServerHttpRequest request = exchange.getRequest();

        // 2.获取相应对象
        ServerHttpResponse response = exchange.getResponse();
        // 3.当前请求是否登录，是 直接放行
        if(request.getURI().getPath().contains("/login")) {
            return chain.filter(exchange);
        }

        // 4.获取所有请求头信息
        HttpHeaders headers = request.getHeaders();
        // 5.获取令牌信息
        String jwtToken = headers.getFirst("token");
        // 6.当前令牌是否存在，不存在，向客户端返回令牌信息
        if(StringUtil.isNullOrEmpty(jwtToken)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 6.1 判断令牌是否合法，返回错误信息
        try {
            // 解析令牌
            JwtUtil.parseJWT(jwtToken);
        }catch (Exception e) {
            e.printStackTrace();
            // 令牌解析失败
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 6.2 令牌合法 放行

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
