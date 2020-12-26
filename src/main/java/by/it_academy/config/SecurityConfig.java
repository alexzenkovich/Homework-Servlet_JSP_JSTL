package by.it_academy.config;

import by.it_academy.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private UserSecurityService userSecurityService;

    @Autowired
    public void setUserSecurityService(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/styles/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/books/**").hasAuthority("ADMINISTRATOR")
                .antMatchers("/", "/index", "/styles/**", "/error").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/index");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        http.csrf().disable();

        http.exceptionHandling().accessDeniedPage("/error");

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation()
                    .newSession()
                .maximumSessions(1)
                .expiredUrl("/index");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .usersByUsernameQuery("select name, surname, email, age from usrs u " +
                "inner join authenticate a on u.id = a.user_id where a.login = ?")
        .authoritiesByUsernameQuery("select a.login, ur.roles from usrs u " +
                "inner join user_role ur on u.id = ur.user_id" +
                "inner join authenticate a on u.id = a.user_id" +
                "where a.login=?");
    }
}
