package org.example.apigateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class AuthenticationFilter implements GatewayFilterFactory {

    // Сам метод не работает и ничего не фильтрует поэтому все захардкодено
    // String jwt = new AuthController().getJwt(); // метод который берет JWT токен из аутентификации
    //и не забудь что токен надо проверить потом. есть ли у него уже токен
    @Override
    public GatewayFilter apply(Object config) {

        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuQG4ucnUiLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTcxMjIwMTY1NywiZXhwIjoxNzEyMjAxNjYwfQ.BJBaktxx15Xh5npLLivNjsktYBRa3hhoe5KfrnITsqw";

        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, authHeader + " " + jwt);
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
