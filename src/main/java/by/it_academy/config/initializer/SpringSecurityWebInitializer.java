package by.it_academy.config.initializer;

import by.it_academy.config.ApplicationConfig;
import by.it_academy.config.SecurityConfig;
import by.it_academy.config.WebConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

public class SpringSecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
}
