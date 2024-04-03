package org.example.apigateway.filter;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.controller.AuthController;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements GatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        String jwt = new AuthController().getJwt(); // метод который берет JWT токен из аутентификации
        //попробуй от руки закинуть  jwt в стрингу захардкодить тут сам jwt токен
        //и не забудь что токен надо проверить потом. есть ли у него уже токен
//        String jwt = "her";

        return (exchange, chain) -> {
            exchange.getRequest()
                    .mutate()
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt));

            return chain.filter(exchange);
        };
    }

    @Override
    public Config newConfig() {
        return new Config();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
    }
}
