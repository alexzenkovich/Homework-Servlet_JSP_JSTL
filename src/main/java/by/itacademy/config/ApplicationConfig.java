package by.itacademy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(PersistanceConfig.class)
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

}
