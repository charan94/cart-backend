package org.commkart.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class StaticContentConfig {

    @Bean
    public RouterFunction<ServerResponse> htmlRouter(@Value("classpath:/static/login.html") Resource html) {
        return route(
                GET("/auth/login"),
                request -> ok()
                        .contentType(MediaType.TEXT_HTML)
                        .syncBody(html)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> imgRouter() {
        return RouterFunctions.resources("/static/**", new ClassPathResource("static/"));
    }

}