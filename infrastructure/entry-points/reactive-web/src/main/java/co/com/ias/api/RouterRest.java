package co.com.ias.api;

import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        String param = "/{page}-{size}";
        String paramid = "/{id}";
        String paramdoc = "/{document}";
        String paramName = "/{name}";
        return route(GET("/api/users".concat(param)), handler::getEmployeesPage)
                .andRoute(POST("/api/user/create"), handler::saveUser)
                .andRoute(PUT("/api/user/salaryUpdate"), handler::updateSalary)
                .andRoute(POST("/api/user/settlement"), handler::getSettlement)
                .andRoute(GET("/api/user/salaryUpdates".concat(paramid)),handler::salarysUpdates)
                .andRoute(GET("/api/users".concat(paramName)),handler::getEmployeeByName)
                .andRoute(GET("/api/user".concat(paramdoc)),handler::getEmployeesByDoc)
                .andRoute(GET("/error"),handler::getError);
    }

    @Bean
    CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // Possibly...
        // config.applyPermitDefaultValues()
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
