package net.toregard.jerseydemo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

@SpringBootApplication
//@EnableOAuth2Sso
public class JerseydemoApplication extends RepositoryRestConfigurerAdapter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(JerseydemoApplication.class).
                initializers(new SpringApplicationContextInitializer())
                .application()
                .run(args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
    }

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        super.configureConversionService(conversionService);
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        super.configureValidatingRepositoryEventListener(validatingListener);
    }

    @Override
    public void configureExceptionHandlerExceptionResolver(ExceptionHandlerExceptionResolver exceptionResolver) {
        super.configureExceptionHandlerExceptionResolver(exceptionResolver);
    }

    @Override
    public void configureHttpMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        super.configureHttpMessageConverters(messageConverters);
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        super.configureJacksonObjectMapper(objectMapper);
    }
}
