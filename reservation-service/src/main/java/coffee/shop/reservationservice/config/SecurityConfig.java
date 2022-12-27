package coffee.shop.reservationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //TODO:Add Authorization validation configurations
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("**")
                .permitAll();
        return http.build();
    }
}
