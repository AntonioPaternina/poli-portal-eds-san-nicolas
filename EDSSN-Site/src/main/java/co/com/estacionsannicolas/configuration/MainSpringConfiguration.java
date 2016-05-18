package co.com.estacionsannicolas.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    PersistenceSpringConfiguration.class,
    SpringMvcConfiguration.class,
    SpringSecurityConfiguration.class,
    SpringBeansConfiguration.class
})
public class MainSpringConfiguration {

}
