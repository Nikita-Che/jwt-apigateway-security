package by.javaguru.config;

import by.javaguru.converter.KCRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // включение механизма для защиты методов по ролям
public class SecurityConfig {

    // фильтр чейн без ролей для 3.2.4 бута
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
//        return serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**")
//
//                        .permitAll()
//                        .anyExchange().authenticated())
//                .oauth2ResourceServer((oAuth -> oAuth.jwt(Customizer.withDefaults()))).build();
//
//    }

//     фильтр чейн с ролями не работают для 3.2.4 бута
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter());


        return serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**").permitAll() // Разрешаем доступ к /eureka/**
                        .pathMatchers("/children").hasRole("admin")
                        .pathMatchers("/training").hasRole("user") // Требуем наличие роли admin для доступа к /children
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oAuth -> oAuth.jwt(Customizer.withDefaults()))
                .build();
    }

    // фильтр чейн с клоак сервера на 2.7.3 буте
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        // конвертер для настройки spring security
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        // подключаем конвертер ролей
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter());
//
//
//        // все сетевые настройки
//        http.authorizeRequests()
//                .antMatchers("/test/login").permitAll() // анонимный пользователь сможет выполнять запросы только по этим URI
//                .antMatchers("/user/*").hasRole("user") //глобально задаем ограничения по классам
//                .antMatchers("/admin/*").hasRole("admin")
//                .anyRequest().authenticated() // остальной API будет доступен только аутентифицированным пользователям
//
//                .and() // добавляем новые настройки, не связанные с предыдущими
//
//                .oauth2ResourceServer()// добавляем конвертер ролей из JWT в Authority (Role)
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter);
//
//        return http.build();
//    }
}
