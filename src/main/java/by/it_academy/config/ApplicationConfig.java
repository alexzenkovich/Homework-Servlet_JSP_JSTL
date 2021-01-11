package by.it_academy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"by.it_academy.services"})
@Import(PersistanceConfig.class)
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

}
