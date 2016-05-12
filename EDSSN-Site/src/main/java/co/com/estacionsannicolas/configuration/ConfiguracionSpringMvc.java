package co.com.estacionsannicolas.configuration;

import co.com.estacionsannicolas.converter.ConvertidorRolPerfilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "co.com.estacionsannicolas")
public class ConfiguracionSpringMvc extends WebMvcConfigurerAdapter {

    @Autowired
    private ConvertidorRolPerfilUsuario convertidorRolPerfilUsuario;

    /**
     * Configurar un ViewResolver
     *
     * @param viewResolverRegistry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/vistas/");
        internalResourceViewResolver.setSuffix(".jsp");
        viewResolverRegistry.viewResolver(internalResourceViewResolver);
    }

    /**
     * Configurar un ResourceHandler para los rescursos estáticos
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/recursos/**").addResourceLocations("/recursos/");
    }

    /**
     * Configurar un MessageSource para los mensajes localizados de la
     * aplicación
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    /**
     * Optional. It's only required when handling '.' in @PathVariables which
     * otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164],
     * still present in Spring 4.1.7. This is a workaround for this issue.
     *
     * @param matcher
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

    /**
     * Configure Converter to be used. In our example, we need a converter to
     * convert string values[Roles] to UserProfiles in newUser.jsp
     *
     * @param formatterRegistry
     */
    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addConverter(convertidorRolPerfilUsuario);
    }
}
