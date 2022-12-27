package coffee.shop.reservationservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Bean configuration for service
 */
@Component
public class BeanConfigurations {
    /**
     * RestTemplate bean configuration to enable functionalities between services
     * @param builder RestTemplateBuilder
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
